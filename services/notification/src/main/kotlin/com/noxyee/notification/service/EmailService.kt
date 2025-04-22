package com.noxyee.notification.service

import com.noxyee.notification.dao.NotificationDocument
import com.noxyee.notification.dao.NotificationRepository
import com.noxyee.notification.dao.NotificationType
import com.noxyee.notification.kafka.model.TaskExpiredMessage
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val springTemplateEngine: SpringTemplateEngine,
    private val notificationRepository: NotificationRepository,
    @Value("\${spring.mail.username}") private val mailSender: String
) {

    @Async
    fun sendEmail(notificationDocument: NotificationDocument, user: UserRepresentation, notificationMessage: Any) {
        val mimeMessage = javaMailSender.createMimeMessage()
        val mimeMessageHelper =
            MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name())

        mimeMessageHelper.setFrom(mailSender)
        mimeMessageHelper.setTo(notificationDocument.recipient)
        val variables = getVariables(notificationDocument, user, notificationMessage)
        mimeMessageHelper.setSubject(notificationDocument.notificationType.subject)
        val context = Context()
        context.setVariables(variables)
        val htmlTemplate = springTemplateEngine.process(notificationDocument.notificationType.templateName, context)
        mimeMessageHelper.setText(htmlTemplate, true)
        javaMailSender.send(mimeMessage)
        notificationDocument.sentAt = LocalDateTime.now()
        notificationRepository.save(notificationDocument)
    }

    private fun getVariables(
        notificationDocument: NotificationDocument,
        user: UserRepresentation,
        notificationMessage: Any
    ): Map<String, String> {
        var variables: Map<String, String> = HashMap()
        when (notificationDocument.notificationType) {
            NotificationType.EMAIL_TASK_EXPIRED -> {
                variables = mapOf(
                    "userName" to user.firstName,
                    "title" to (notificationMessage as TaskExpiredMessage).title!!,
                    "dueDate" to notificationMessage.dueDate.toString()
                )
            }
        }
        return variables
    }

}