package com.noxyee.task.api

import com.noxyee.task.model.CreateTaskRequest
import com.noxyee.task.model.TaskResponse
import com.noxyee.task.model.UpdateTaskRequest
import com.noxyee.task.service.TaskService
import org.springframework.web.bind.annotation.*

@RestController
class TaskController(private val taskService: TaskService) {

    @GetMapping("/api/v1/tasks/{noteId}")
    fun getTasks(@PathVariable noteId: String): List<TaskResponse> = taskService.getTasksByNoteId(noteId)

    @PostMapping("/api/v1/tasks")
    fun createTask(@RequestBody createTaskRequest: CreateTaskRequest): TaskResponse =
        taskService.createTask(createTaskRequest)

    @PutMapping("/api/v1/tasks/{taskId}")
    fun updateTask(
        @PathVariable("taskId") taskId: String,
        @RequestBody updateTaskRequest: UpdateTaskRequest
    ): TaskResponse =
        taskService.updateTask(taskId, updateTaskRequest)

    @DeleteMapping("/api/v1/tasks/{taskId}")
    fun deleteTask(@PathVariable("taskId") taskId: String) = taskService.deleteTask(taskId)

    @DeleteMapping("/api/v1/tasks/{noteId}/all")
    fun deleteTasksByNoteId(@PathVariable("noteId") noteId: String) = taskService.deleteTasksByNoteId(noteId)
}