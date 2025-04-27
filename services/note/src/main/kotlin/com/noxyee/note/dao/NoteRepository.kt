package com.noxyee.note.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : MongoRepository<NoteDocument, String> {
    fun findAllByUserId(userId: String, pageable: Pageable): Page<NoteDocument>
    fun findAllByTagIdsContaining(tagId: String): List<NoteDocument>
}