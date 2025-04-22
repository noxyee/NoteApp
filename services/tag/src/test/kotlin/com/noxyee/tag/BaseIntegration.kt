package com.noxyee.tag

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.kafka.KafkaContainer

@Testcontainers
@ActiveProfiles("test")
open class BaseIntegration {


    companion object {
        @Container
        @ServiceConnection
        val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:latest")

        @Container
        @ServiceConnection
        val kafkaContainer = KafkaContainer("apache/kafka")
    }
}