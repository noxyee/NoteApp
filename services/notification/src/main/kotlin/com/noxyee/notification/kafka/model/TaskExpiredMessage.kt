package com.noxyee.notification.kafka.model

import java.time.LocalDateTime

data class TaskExpiredMessage(
    val userId: String? = null,
    val title: String? = null,
    val dueDate: LocalDateTime? = null
)