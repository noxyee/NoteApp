package com.noxyee.task.service

import com.noxyee.task.dao.TaskRepository
import com.noxyee.task.model.TaskResponse
import com.noxyee.task.util.TaskMapper
import org.springframework.stereotype.Service

@Service
class TagService(
    private val taskService: TaskService,
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {

    fun addTagToTask(taskId: String, tagId: String): TaskResponse {
        val taskDocument = taskService.getTaskById(taskId)
        taskDocument.addTag(tagId)
        return taskMapper.mapTaskDocumentToTaskResponse(taskRepository.save(taskDocument))
    }

    fun removeTagFromTask(taskId: String, tagId: String): TaskResponse {
        val taskDocument = taskService.getTaskById(taskId)
        taskDocument.removeTag(tagId)
        return taskMapper.mapTaskDocumentToTaskResponse(taskRepository.save(taskDocument))
    }

    fun deleteTagFromTasks(tagId: String) {
        val taskDocuments = taskRepository.findAllByTagIdsContaining(tagId)
        taskDocuments.forEach {
            it.removeTag(tagId)
        }
        taskRepository.saveAll(taskDocuments)
    }
}