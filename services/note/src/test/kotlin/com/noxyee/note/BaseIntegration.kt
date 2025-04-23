package com.noxyee.note

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.kafka.KafkaContainer

@Testcontainers
@ActiveProfiles("test")
open class BaseIntegration {


    companion object {
        @Container
        @ServiceConnection
        val mongoDBContainer = MongoDBContainer("mongo:latest")

        @Container
        @ServiceConnection
        val kafkaContainer = KafkaContainer("apache/kafka")
    }
}