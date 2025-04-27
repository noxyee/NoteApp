package com.noxyee.tag.api

import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.TagResponse
import com.noxyee.tag.model.UpdateTagRequest
import com.noxyee.tag.service.TagService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
class TagController(private val tagService: TagService) {

    @GetMapping("/api/v1/tags/{userId}")
    fun getTags(
        @PathVariable("userId") userId: String, @PageableDefault(
            size = 20, page = 0, sort = ["createdAt"], direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): Page<TagResponse> = tagService.getTags(userId, pageable)

    @PostMapping("/api/v1/tags")
    fun createTag(@RequestBody @Valid createTagRequest: CreateTagRequest): TagResponse =
        tagService.createTag(createTagRequest)

    @PutMapping("/api/v1/tags/{tagId}")
    fun updateTag(
        @PathVariable("tagId") tagId: String,
        @RequestBody @Valid updateTagRequest: UpdateTagRequest
    ): TagResponse =
        tagService.updateTag(updateTagRequest, tagId)

    @DeleteMapping("/api/v1/tags/{tagId}")
    fun deleteTag(@PathVariable("tagId") tagId: String) = tagService.deleteTag(tagId)
}