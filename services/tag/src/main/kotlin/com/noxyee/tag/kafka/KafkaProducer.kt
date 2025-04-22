package com.noxyee.tag.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    @Value("\${kafka.topic.tag-delete}")
    private lateinit var tagDeleteTopic: String

    fun sendTagDeletedMessage(tagId: String) {
        val message = MessageBuilder
            .withPayload(tagId)
            .setHeader(KafkaHeaders.TOPIC, tagDeleteTopic)
            .build()
        kafkaTemplate.send(message)

    }
}