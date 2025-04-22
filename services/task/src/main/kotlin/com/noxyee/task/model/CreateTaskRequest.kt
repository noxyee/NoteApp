package com.noxyee.task.model

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime

data class CreateTaskRequest(
    @field:NotBlank @field:Length(max = 50) val title: String,
    @field:NotBlank val content: String,
    @field:NotBlank @field: Length(min = 36, max = 36) val userId: String,
    @field:NotBlank @field: Length(min = 20, max = 20) val noteId: String,
    val dueDate: LocalDateTime? = null,
    val sendMailAfterDueDate: Boolean = false,
    val tagIds : Set<String> = emptySet()
)
