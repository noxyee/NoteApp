package com.noxyee.note.kafka

import com.noxyee.note.service.TagService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TagDeletedConsumer(private val tagService: TagService) {

    private val logger = LoggerFactory.getLogger(TagDeletedConsumer::class.java)

    @KafkaListener(topics = ["\${kafka.topic.tag-delete}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consumeNotificationMessage(message: ConsumerRecord<String, String>) {
        logger.info("Received tag deleted message: ${message.value()}")
        tagService.removeTagFromNotes(message.value())
    }
}