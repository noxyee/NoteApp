package com.noxyee.note.service

import com.noxyee.note.dao.NoteRepository
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.util.NoteMapper
import org.springframework.stereotype.Service

@Service
class TagService(
    private val noteService: NoteService,
    private val noteRepository: NoteRepository,
    private val noteMapper: NoteMapper
) {

    fun removeTagFromNote(noteId: String, tagId: String): NoteResponse {
        val note = noteService.getNoteById(noteId)
        note.removeTag(tagId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }

    fun removeTagFromNotes(tagId: String) {
        val notes = noteRepository.findAllByTagIdsContaining(tagId)
        notes.forEach { note ->
            note.removeTag(tagId)
        }
        noteRepository.saveAll(notes)
    }

    fun addTagToNote(noteId: String, tagId: String): NoteResponse {
        val note = noteService.getNoteById(noteId)
        note.addTag(tagId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }
}