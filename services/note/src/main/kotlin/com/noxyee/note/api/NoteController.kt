package com.noxyee.note.api

import com.noxyee.note.model.CreateNoteRequest
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.model.UpdateNoteRequest
import com.noxyee.note.service.NoteService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class NoteController(private val noteService: NoteService) {

    @GetMapping("/api/v1/notes/{userId}")
    fun getUserNotes(@PathVariable("userId") userId: String): List<NoteResponse> {
        return noteService.getUserNotes(userId)
    }

    @PostMapping("/api/v1/notes")
    fun createNote(@RequestBody @Valid createNoteRequest: CreateNoteRequest): NoteResponse {
        return noteService.createNote(createNoteRequest)
    }

    @PutMapping("/api/v1/notes/{noteId}")
    fun updateNote(
        @PathVariable("noteId") noteId: String,
        @RequestBody @Valid updateNoteRequest: UpdateNoteRequest
    ): NoteResponse {
        return noteService.updateNote(updateNoteRequest, noteId)
    }

    @DeleteMapping("/api/v1/notes/{noteId}")
    fun deleteNote(@PathVariable noteId: String) {
        noteService.deleteNote(noteId)
    }
}