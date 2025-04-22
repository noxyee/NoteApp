package com.noxyee.notification.kafka

import com.noxyee.notification.kafka.model.TaskExpiredMessage
import com.noxyee.notification.service.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class NotificationConsumer(private val notificationService: NotificationService) {

    private val logger = LoggerFactory.getLogger(NotificationConsumer::class.java)

    @KafkaListener(topics = ["\${kafka.topic.task-expired}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consumeTaskExpiredMessage(taskExpiredMessage: TaskExpiredMessage) {
        logger.info("Received notification message: $taskExpiredMessage")
        notificationService.consumeTaskExpiredMessage(taskExpiredMessage)
    }
}