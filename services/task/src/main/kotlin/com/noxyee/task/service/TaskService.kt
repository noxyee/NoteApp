package com.noxyee.task.service

import com.noxyee.task.client.NoteClient
import com.noxyee.task.dao.TaskDocument
import com.noxyee.task.dao.TaskRepository
import com.noxyee.task.exception.model.TaskNotFoundException
import com.noxyee.task.model.CreateTaskRequest
import com.noxyee.task.model.TaskResponse
import com.noxyee.task.model.UpdateTaskRequest
import com.noxyee.task.util.TaskMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper,
    private val noteClient: NoteClient
) {

    fun getTaskById(taskId: String): TaskDocument {
        return taskRepository.findById(taskId)
            .orElseThrow { TaskNotFoundException("Task with id: $taskId not found") }
    }

    fun getTasksByNoteId(noteId: String, pageable: Pageable): Page<TaskResponse> {
        return taskRepository.findAllByNoteId(noteId, pageable)
            .map(taskMapper::mapTaskDocumentToTaskResponse)
    }

    @Transactional
    fun createTask(createTaskRequest: CreateTaskRequest): TaskResponse {
        val taskDocument = taskRepository.save(
            taskMapper.mapCreateTaskRequestToTaskDocument(createTaskRequest)
        )
        noteClient.addTaskToNote(taskDocument.noteId, taskDocument.id!!)
        return taskMapper.mapTaskDocumentToTaskResponse(taskDocument)
    }

    fun updateTask(taskId: String, updateTaskRequest: UpdateTaskRequest): TaskResponse {
        val taskDocument = getTaskById(taskId)
        taskMapper.mergeUpdateTaskRequestToTaskDocument(updateTaskRequest, taskDocument)
        return taskMapper.mapTaskDocumentToTaskResponse(taskRepository.save(taskDocument))
    }

    @Transactional
    fun deleteTask(taskId: String) {
        val taskDocument = getTaskById(taskId)
        noteClient.deleteTaskFromNote(taskDocument.noteId, taskId)
        taskRepository.delete(taskDocument)
    }

    fun deleteTasksByNoteId(noteId: String) {
        taskRepository.deleteAllByNoteId(noteId)
    }
}