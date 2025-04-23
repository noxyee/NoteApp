package com.noxyee.note.dao

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "note")
data class NoteDocument(
    @Id
    val id: String? = null,
    @Length(max = 100)
    var title: String? = null,
    var content: String? = null,
    @NotBlank
    val userId: String,
    var tagIds: Set<String> = emptySet(),
    var taskIds: Set<String> = emptySet(),
    var imageIds: List<String> = mutableListOf(),
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

    fun removeTask(taskId: String) {
        taskIds = taskIds - taskId
    }

    fun addTask(taskId: String) {
        taskIds = taskIds + taskId
    }

    fun addTag(tagId: String) {
        tagIds = tagIds + tagId
    }
    fun removeTag(tagId: String) {
        tagIds = tagIds - tagId
    }

    fun addImage(imageId: String) {
        imageIds = imageIds + imageId
    }

    fun removeImage(imageId: String) {
        imageIds = imageIds.filter { it != imageId }
    }
}
