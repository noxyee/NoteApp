package com.noxyee.notification.dao

enum class NotificationType(val templateName: String, val subject: String) {
    EMAIL_TASK_EXPIRED("task-expired.html", "Tasks expired"),
}