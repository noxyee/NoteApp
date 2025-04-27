package com.noxyee.tag.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<TagEntity, String> {
    fun findAllByUserId(userId: String, pageable: Pageable): Page<TagEntity>
}