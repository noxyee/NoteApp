package com.noxyee.task.util

import com.noxyee.task.dao.TaskDocument
import com.noxyee.task.model.CreateTaskRequest
import com.noxyee.task.model.TaskResponse
import com.noxyee.task.model.UpdateTaskRequest
import org.springframework.stereotype.Component

@Component
class TaskMapper {

    fun mapTaskDocumentToTaskResponse(taskDocument: TaskDocument): TaskResponse {
        return TaskResponse(
            id = taskDocument.id!!,
            title = taskDocument.title,
            content = taskDocument.content,
            userId = taskDocument.userId,
            noteId = taskDocument.noteId,
            tagIds = taskDocument.tagIds,
            dueDate = taskDocument.dueDate,
            done = taskDocument.done,
            sendMailAfterDueDate = taskDocument.sendMailAfterDueDate
        )
    }

    fun mapCreateTaskRequestToTaskDocument(createTaskRequest: CreateTaskRequest): TaskDocument {
        return TaskDocument(
            title = createTaskRequest.title,
            content = createTaskRequest.content,
            userId = createTaskRequest.userId,
            noteId = createTaskRequest.noteId,
            dueDate = createTaskRequest.dueDate,
            sendMailAfterDueDate = createTaskRequest.sendMailAfterDueDate,
            tagIds = createTaskRequest.tagIds
        )
    }

    fun mergeUpdateTaskRequestToTaskDocument(updateTaskRequest: UpdateTaskRequest, taskDocument: TaskDocument) {
        taskDocument.apply {
            title = updateTaskRequest.title ?: title
            content = updateTaskRequest.content ?: content
            dueDate = updateTaskRequest.dueDate ?: dueDate
            done = updateTaskRequest.done ?: done
            sendMailAfterDueDate = updateTaskRequest.sendMailAfterDueDate ?: sendMailAfterDueDate
        }
    }
}