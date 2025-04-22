package com.noxyee.task.api

import com.noxyee.task.model.TaskResponse
import com.noxyee.task.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TagController(private val tagService: TagService) {

    @PutMapping("/api/v1/notes/{taskId}/tags/{tagId}")
    fun addTagsToNote(@PathVariable("taskId") taskId: String, @PathVariable("tagId") tagId: String): TaskResponse =
        tagService.addTagToTask(taskId, tagId)

    @DeleteMapping("/api/v1/notes/{taskId}/tags/{tagId}")
    fun removeTagsFromNote(@PathVariable("taskId") taskId: String, @PathVariable("tagId") tagId: String): TaskResponse =
        tagService.removeTagFromTask(taskId, tagId)
}