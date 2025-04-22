package com.noxyee.notification.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.noxyee.notification.dao.NotificationDocument
import com.noxyee.notification.dao.NotificationRepository
import com.noxyee.notification.dao.NotificationType
import com.noxyee.notification.kafka.model.TaskExpiredMessage
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val emailService: EmailService,
    private val objectMapper: ObjectMapper,
    private val keycloakUserService: KeycloakUserService
) {
    fun consumeTaskExpiredMessage(taskExpiredMessage: TaskExpiredMessage) {
        val user = keycloakUserService.getUserById(taskExpiredMessage.userId!!)
            ?: throw IllegalArgumentException("User not found with ID: ${taskExpiredMessage.userId}")
        val payload = objectMapper.writeValueAsString(taskExpiredMessage)
        val notificationDocument = NotificationDocument(
            notificationType = NotificationType.EMAIL_TASK_EXPIRED,
            payload = payload,
            recipient = user.email
        )
        notificationRepository.save(notificationDocument)
        emailService.sendEmail(notificationDocument, user, taskExpiredMessage)
    }
}