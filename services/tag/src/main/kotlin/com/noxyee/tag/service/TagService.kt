package com.noxyee.tag.service

import com.noxyee.tag.config.RedisConfig.Companion.TAGS_CACHE_NAME
import com.noxyee.tag.dao.TagEntity
import com.noxyee.tag.dao.TagRepository
import com.noxyee.tag.exception.model.TagNotFoundException
import com.noxyee.tag.kafka.KafkaProducer
import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.PageDTO
import com.noxyee.tag.model.TagResponse
import com.noxyee.tag.model.UpdateTagRequest
import com.noxyee.tag.util.TagMapper
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val tagMapper: TagMapper,
    private val kafkaProducer: KafkaProducer,
    private val cacheManagementService: CacheManagementService,
) {

    @Cacheable(
        cacheNames = [TAGS_CACHE_NAME],
        key = "{#userId, #pageable.pageNumber, #pageable.pageSize, #pageable.sort}"
    )
    fun getTags(userId: String, pageable: Pageable): PageDTO<TagResponse> {
        return PageDTO.fromPage(
            tagRepository.findAllByUserId(userId, pageable).map(tagMapper::mapEntityToTagResponse)
        )
    }

    fun createTag(createTagRequest: CreateTagRequest): TagResponse {
        val tagEntity = tagMapper.mapCreateNoteRequestToTagEntity(createTagRequest)
        val savedEntity = tagRepository.save(tagEntity)
        cacheManagementService.removeTagsCacheByUserId(savedEntity.userId)
        return tagMapper.mapEntityToTagResponse(savedEntity)
    }

    @Transactional
    fun updateTag(updateTagRequest: UpdateTagRequest, tagId: String): TagResponse {
        val tagEntity = findTagById(tagId)
        tagMapper.mergeUpdateTagRequestToTagEntity(updateTagRequest, tagEntity)
        val savedEntity = tagRepository.save(tagEntity)
        cacheManagementService.removeTagsCacheByUserId(savedEntity.userId)
        return tagMapper.mapEntityToTagResponse(savedEntity)
    }

    @Transactional
    fun deleteTag(tagId: String) {
        val tagEntity = findTagById(tagId)
        tagRepository.delete(tagEntity)
        cacheManagementService.removeTagsCacheByUserId(tagEntity.userId)
        kafkaProducer.sendTagDeletedMessage(tagId)
    }

    private fun findTagById(tagId: String): TagEntity {
        return tagRepository.findById(tagId)
            .orElseThrow { TagNotFoundException("Tag with id: $tagId not found") }
    }
}