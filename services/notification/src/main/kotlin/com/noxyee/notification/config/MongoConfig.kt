package com.noxyee.notification.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager

@Configuration
class MongoConfig {

    @Bean
    fun mongoTransactionManager(mongoDbFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(mongoDbFactory)
    }
}