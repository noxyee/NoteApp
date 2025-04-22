package com.noxyee.note.service

import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.client.gridfs.model.GridFSDownloadOptions
import com.noxyee.note.dao.NoteDocument
import com.noxyee.note.dao.NoteRepository
import com.noxyee.note.exception.model.NoteNotFoundException
import com.noxyee.note.model.NoteResponse
import com.noxyee.note.util.NoteMapper
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsResource
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ImageService(
    private val gridFsTemplate: GridFsTemplate,
    private val noteRepository: NoteRepository,
    private val noteService: NoteService,
    private val noteMapper: NoteMapper
) {
    fun addImageToNote(noteId: String, file: MultipartFile): NoteResponse {
        val note = noteService.getNoteById(noteId)
        val metadata = BasicDBObject()
        metadata["noteId"] = noteId
        metadata["_contentType"] = noteId

        val imageId = try {
            gridFsTemplate.store(
                file.inputStream,
                file.originalFilename,
                file.contentType,
                metadata
            ).toString()
        } catch (e: IOException) {
            throw RuntimeException("Failed to store image", e)
        }

        note.addImage(imageId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }

    fun getImage(imageId: String): GridFsResource {
        val objectId = getObjectId(imageId)

        val gridFsFile = gridFsTemplate.findOne(Query(Criteria.where("_id").`is`(objectId)))
            ?: throw RuntimeException("Image not found with id: $imageId")

        return gridFsTemplate.getResource(gridFsFile)
    }

    fun deleteImage(noteId: String, imageId: String): NoteResponse {
        val note = noteService.getNoteById(noteId)

        if (!note.imageIds.contains(imageId)) {
            throw RuntimeException("Image with id: $imageId not found in note with id: $noteId")
        }

        val objectId = getObjectId(imageId)

        gridFsTemplate.delete(Query(Criteria.where("_id").`is`(objectId)))

        note.removeImage(imageId)
        val savedNote = noteRepository.save(note)
        return noteMapper.mapNoteDocumentToNoteResponse(savedNote)
    }

    fun deleteAllImagesForNote(noteId: String) {
        gridFsTemplate.delete(Query(Criteria.where("metadata.noteId").`is`(noteId)))
    }

    private fun getObjectId(id: String): ObjectId {
        return try {
            ObjectId(id)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid image ID format")
        }
    }
}
