package com.noxyee.note.migration

import com.noxyee.note.dao.NoteDocument
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.LocalDateTime
import java.util.*

@Profile("test")
@ChangeUnit(
    id = "test-data-initialization",
    order = "999999",
    author = "tborkowski",
    systemVersion = "1"
)
class TestDataChangelog {

    @Execution
    fun execution(mongoTemplate: MongoTemplate) {
        val sampleNotes = listOf(
            Pair("Przepis na sernik", "Składniki:\n- 1kg sera\n- 4 jajka\n..."),
            Pair("Lista zakupów", "1. Mleko\n2. Chleb\n3. Masło"),
            Pair("Pomysły na wakacje", "1. Grecja\n2. Chorwacja\n3. Włochy"),
            Pair("Notatki ze spotkania", "Omówione tematy:\n- Budżet\n- Harmonogram\n- Zespół"),
            Pair("Zadania do zrobienia", "- Zapłacić rachunki\n- Umówić wizytę u dentysty")
        )

        val now = LocalDateTime.now()
        var counter = 0

        sampleNotes.forEach { (title, content) ->
            val createdAt = now.minusDays((1..30).random().toLong())
            val updatedAt = createdAt.plusDays((1..5).random().toLong())

            val note = NoteDocument(
                id = UUID.randomUUID().toString(),
                title = title,
                content = content,
                userId = "09eff104-d734-4729-8d26-b4782b4e2f69",
                createdAt = createdAt,
                updatedAt = updatedAt
            )

            note.taskIds + "testTask1"
            note.taskIds + "testTask2"

            if (counter % 2 == 0) {
                note.tagIds + "testTag1"
                note.tagIds + "testTag2"
            }

            if (counter % 3 == 0) {
                note.imageIds + ("testImage${counter}_1")
                note.imageIds + ("testImage${counter}_2")
            }

            mongoTemplate.save(note)
            counter++
        }
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        val now = LocalDateTime.now()
        val monthAgo = now.minusMonths(1)

        mongoTemplate.findAllAndRemove(
            Query(
                Criteria.where("createdAt").gte(monthAgo)
            ),
            NoteDocument::class.java
        )
    }
}