package com.noxyee.note.migration

import com.noxyee.note.dao.NoteDocument
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(
    id = "note-init",
    order = "1",
    author = "tborkowski",
    systemVersion = "1"
)
class NoteInitializerChangelog {

    @Execution
    fun execution(mongoTemplate: MongoTemplate) {
        if (!mongoTemplate.collectionExists(NoteDocument::class.java)) {
            mongoTemplate.createCollection(NoteDocument::class.java)
        }
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        mongoTemplate.dropCollection(NoteDocument::class.java)
    }
}