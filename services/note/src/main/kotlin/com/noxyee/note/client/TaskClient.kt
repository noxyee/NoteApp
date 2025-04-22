package com.noxyee.note.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("task")
interface TaskClient {

    @DeleteMapping("/api/v1/tasks/{noteId}/all")
    fun deleteTasksByNoteId(@PathVariable("noteId") noteId: String)
}