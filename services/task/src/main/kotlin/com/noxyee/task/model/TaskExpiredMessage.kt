package com.noxyee.task.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class TaskExpiredMessage(
    val userId: String,
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val dueDate: LocalDateTime
)
