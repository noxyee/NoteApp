package com.noxyee.task.model

import java.time.LocalDateTime

data class UpdateTaskRequest(
    val title: String?,
    val content: String?,
    var dueDate: LocalDateTime?,
    var done: Boolean?,
    var sendMailAfterDueDate: Boolean?,
)
