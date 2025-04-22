package com.noxyee.note.api

import com.noxyee.note.model.NoteResponse
import com.noxyee.note.service.TagService
import org.springframework.web.bind.annotation.*

@RestController
class TagController(private val tagService: TagService) {

    @PutMapping("/api/v1/notes/{noteId}/tags/{tagId}")
    fun addTagToNote(@PathVariable("noteId") noteId: String, @PathVariable("tagId") tagId: String): NoteResponse =
        tagService.addTagToNote(noteId, tagId)

    @DeleteMapping("/api/v1/notes/{noteId}/tags/{tagId}")
    fun removeTagFromNote(@PathVariable("noteId") noteId: String, @PathVariable("tagId") tagId: String): NoteResponse =
        tagService.removeTagFromNote(noteId, tagId)
}