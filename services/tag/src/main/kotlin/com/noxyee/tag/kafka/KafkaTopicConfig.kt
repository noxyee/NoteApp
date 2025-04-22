package com.noxyee.tag.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {

    @Value("\${kafka.topic.tag-delete}")
    private lateinit var tagDeleteTopic: String

    @Bean
    fun tagTopic(): NewTopic {
        return TopicBuilder.name(tagDeleteTopic)
            .build()
    }
}