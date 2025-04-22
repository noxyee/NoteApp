package com.noxyee.task.kafka.producer

import com.noxyee.task.model.TaskExpiredMessage
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class TaskExpiredProducer(@Qualifier("jsonKafkaTemplate") private val kafkaTemplate: KafkaTemplate<String, Any>) {

    @Value("\${kafka.topic.task-expired}")
    private lateinit var taskExpiredTopic: String

    fun sendTaskExpiredMessage(message: TaskExpiredMessage) {
        val kafkaMessage = MessageBuilder
            .withPayload(message)
            .setHeader(KafkaHeaders.TOPIC, taskExpiredTopic)
            .build()
        kafkaTemplate.send(kafkaMessage)
    }
}