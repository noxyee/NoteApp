package com.noxyee.task.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

@FeignClient("note")
interface NoteClient {

    @PutMapping("/api/v1/notes/{noteId}/tasks/{taskId}")
    fun addTaskToNote(
        @PathVariable noteId: String,
        @PathVariable taskId: String
    )

    @DeleteMapping("/api/v1/notes/{noteId}/tasks/{taskId}")
    fun deleteTaskFromNote(
        @PathVariable noteId: String,
        @PathVariable taskId: String
    )
}