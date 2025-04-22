package com.noxyee.tag.api

import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.TagResponse
import com.noxyee.tag.model.UpdateTagRequest
import com.noxyee.tag.service.TagService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class TagController(private val tagService: TagService) {

    @GetMapping("/api/v1/tags/{userId}")
    fun getTags(@PathVariable("userId") userId: String): List<TagResponse> = tagService.getTags(userId)

    @PostMapping("/api/v1/tags")
    fun createTag(@RequestBody @Valid createTagRequest: CreateTagRequest): TagResponse =
        tagService.createTag(createTagRequest)

    @PutMapping("/api/v1/tags/{tagId}")
    fun updateTag(@PathVariable("tagId") tagId: String, @RequestBody @Valid updateTagRequest: UpdateTagRequest): TagResponse =
        tagService.updateTag(updateTagRequest, tagId)

    @DeleteMapping("/api/v1/tags/{tagId}")
    fun deleteTag(@PathVariable("tagId") tagId: String) = tagService.deleteTag(tagId)
}