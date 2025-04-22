package com.noxyee.note.api

import com.noxyee.note.model.NoteResponse
import com.noxyee.note.service.TaskService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TaskController(private val taskService: TaskService) {

    @PutMapping("/api/v1/notes/{noteId}/tasks/{taskId}")
    fun addTaskToNote(@PathVariable("noteId") noteId: String, @PathVariable("taskId") taskId: String): NoteResponse =
        taskService.addTaskToNote(noteId, taskId)

    @DeleteMapping("/api/v1/notes/{noteId}/tasks/{taskId}")
    fun deleteTaskFromNote(
        @PathVariable("noteId") noteId: String,
        @PathVariable("taskId") taskId: String
    ): NoteResponse =
        taskService.deleteTaskFromNote(noteId, taskId)
}