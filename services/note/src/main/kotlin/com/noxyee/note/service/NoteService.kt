package com.noxyee.note.service

import com.noxyee.note.client.TaskClient
import com.noxyee.note.dao.NoteDocument
import com.noxyee.note.dao.NoteRepository
import com.noxyee.note.exception.model.NoteNotFoundException
import com.noxyee.note.model.CreateNoteRequest
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.model.UpdateNoteRequest
import com.noxyee.note.util.NoteMapper
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val noteMapper: NoteMapper,
    private val taskClient: TaskClient,
    private val imageService: ImageService
) {

    fun getNoteById(noteId: String): NoteDocument {
        return noteRepository.findById(noteId)
            .orElseThrow { NoteNotFoundException("Note with id: $noteId not found") }
    }

    fun getUserNotes(userId: String): List<NoteResponse> {
        return noteRepository.findAllByUserId(userId)
            .map(noteMapper::mapNoteDocumentToNoteResponse)
    }

    fun createNote(createNoteRequest: CreateNoteRequest): NoteResponse {
        val noteDocument = noteMapper.mapCreateNoteRequestToNoteDocument(createNoteRequest)
        val savedNoteDocument = noteRepository.save(noteDocument)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNoteDocument)
    }

    fun updateNote(updateNoteRequest: UpdateNoteRequest, noteId: String): NoteResponse {
        val noteDocument = getNoteById(noteId)
        noteMapper.mergeUpdateNoteRequestToNoteDocument(updateNoteRequest, noteDocument)
        val savedNoteDocument = noteRepository.save(noteDocument)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNoteDocument)
    }

    fun deleteNote(noteId: String) {
        if (!noteRepository.existsById(noteId)) {
            throw NoteNotFoundException("Note with id: $noteId not found")
        }
        taskClient.deleteTasksByNoteId(noteId)
        imageService.deleteAllImagesForNote(noteId)
        noteRepository.deleteById(noteId)
    }
}
