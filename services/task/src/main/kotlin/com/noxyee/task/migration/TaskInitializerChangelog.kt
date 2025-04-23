package com.noxyee.task.migration

import com.noxyee.task.dao.TaskDocument
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(
    id = "task-init",
    order = "1",
    author = "tborkowski",
    systemVersion = "1"
)
class TaskInitializerChangelog {

    @Execution
    fun execution(mongoTemplate: MongoTemplate) {
        if (!mongoTemplate.collectionExists(TaskDocument::class.java)) {
            mongoTemplate.createCollection(TaskDocument::class.java)
        }
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        mongoTemplate.dropCollection(TaskDocument::class.java)
    }
}