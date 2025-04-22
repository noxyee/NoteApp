package com.noxyee.task.dao

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime


interface TaskRepository : MongoRepository<TaskDocument, String> {
    fun findAllByNoteId(noteId: String): List<TaskDocument>
    fun findAllByTagIdsContaining(tagId: String): List<TaskDocument>
    fun findAllBySendMailAfterDueDateTrueAndDoneFalseAndDueDateBetween(start: LocalDateTime, end: LocalDateTime): List<TaskDocument>
    fun deleteAllByNoteId(noteId: String)
}