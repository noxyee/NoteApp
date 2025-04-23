package com.noxyee.notification.migration

import com.noxyee.notification.dao.NotificationDocument
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = "notification-init", order = "1", author = "tborkowski", systemVersion = "1")
class NotificationInitializerChangelog {
    @Execution
    fun execution(mongoTemplate: MongoTemplate) {
        if (!mongoTemplate.collectionExists(NotificationDocument::class.java)) {
            mongoTemplate.createCollection(NotificationDocument::class.java)
        }
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        mongoTemplate.dropCollection(NotificationDocument::class.java)
    }
}