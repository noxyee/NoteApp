package com.noxyee.note.util

import com.noxyee.note.dao.NoteDocument
import com.noxyee.note.model.CreateNoteRequest
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.model.UpdateNoteRequest
import org.springframework.stereotype.Component

@Component
class NoteMapper {
    fun mapNoteDocumentToNoteResponse(noteDocument: NoteDocument): NoteResponse {
        return NoteResponse(
            id = noteDocument.id!!,
            title = noteDocument.title,
            content = noteDocument.content,
            userId = noteDocument.userId,
            tagIds = noteDocument.tagIds,
            taskIds = noteDocument.taskIds,
            imageIds = noteDocument.imageIds
        )
    }

    fun mapCreateNoteRequestToNoteDocument(createNoteRequest: CreateNoteRequest): NoteDocument {
        return NoteDocument(
            userId = createNoteRequest.userId
        )
    }

    fun mergeUpdateNoteRequestToNoteDocument(updateNoteRequest: UpdateNoteRequest, noteDocument: NoteDocument) {
        noteDocument.apply {
            title = updateNoteRequest.title ?: title
            content = updateNoteRequest.content ?: content
        }
    }
}
