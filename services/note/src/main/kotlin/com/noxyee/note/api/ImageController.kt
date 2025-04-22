package com.noxyee.note.api

import com.noxyee.note.model.NoteResponse
import com.noxyee.note.service.ImageService
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
    private val imageService: ImageService,
) {
    @PostMapping("/api/v1/notes/{noteId}/images")
    fun addImageToNote(
        @PathVariable noteId: String,
        @RequestParam("file") file: MultipartFile
    ): NoteResponse {
        return imageService.addImageToNote(noteId, file)
    }

    @GetMapping("/api/v1/images/{imageId}")
    fun getImage(@PathVariable imageId: String): ResponseEntity<Resource> {
        val resource = imageService.getImage(imageId)
        val contentType = resource.contentType

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\"")
            .body(InputStreamResource(resource.inputStream))
    }

    @DeleteMapping("/api/v1/notes/{noteId}/images/{imageId}")
    fun deleteImage(
        @PathVariable noteId: String,
        @PathVariable imageId: String
    ): NoteResponse {
        return imageService.deleteImage(noteId, imageId)
    }
}