package com.noxyee.task.model

import java.time.LocalDateTime

data class TaskResponse(
    val id: String,
    val title: String,
    val content: String,
    val userId: String,
    val noteId: String,
    val tagIds: Set<String>,
    val dueDate: LocalDateTime? = null,
    val done: Boolean,
    val sendMailAfterDueDate: Boolean
)
