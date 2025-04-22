package com.noxyee.task.dao

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "task")
data class TaskDocument(
    @Id
    val id: String? = null,
    @Length(max = 50)
    var title: String,
    var content: String,
    @NotBlank
    val userId: String,
    @NotBlank
    val noteId: String,
    var tagIds: Set<String> = emptySet(),
    var dueDate: LocalDateTime? = null,
    var done: Boolean = false,
    var sendMailAfterDueDate: Boolean = false,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

    fun addTag(tagId: String) {
        tagIds = tagIds + tagId
    }

    fun removeTag(tagId: String) {
        tagIds = tagIds - tagId
    }
}