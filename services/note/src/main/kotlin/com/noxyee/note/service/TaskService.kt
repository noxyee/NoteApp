package com.noxyee.note.service

import com.noxyee.note.dao.NoteRepository
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.util.NoteMapper
import org.springframework.stereotype.Service

@Service
class TaskService(private val noteService: NoteService, private val noteRepository: NoteRepository, private val noteMapper: NoteMapper) {

    fun addTaskToNote(noteId: String, taskId: String): NoteResponse {
        val note = noteService.getNoteById(noteId)
        note.addTask(taskId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }

    fun deleteTaskFromNote(noteId: String, taskId: String): NoteResponse {
        val note = noteService.getNoteById(noteId)
        note.removeTask(taskId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }
}