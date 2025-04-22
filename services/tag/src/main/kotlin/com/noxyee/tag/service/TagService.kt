package com.noxyee.tag.service

import com.noxyee.tag.dao.TagRepository
import com.noxyee.tag.exception.model.TagNotFoundException
import com.noxyee.tag.kafka.KafkaProducer
import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.TagResponse
import com.noxyee.tag.model.UpdateTagRequest
import com.noxyee.tag.util.TagMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val tagMapper: TagMapper,
    private val kafkaProducer: KafkaProducer
) {

    fun getTags(userId: String): List<TagResponse> {
        return tagRepository.findAllByUserId(userId).map(tagMapper::mapEntityToTagResponse)
    }

    fun createTag(createTagRequest: CreateTagRequest): TagResponse {
        val tagEntity = tagMapper.mapCreateNoteRequestToTagEntity(createTagRequest)
        val savedEntity = tagRepository.save(tagEntity)
        return tagMapper.mapEntityToTagResponse(savedEntity)
    }

    fun updateTag(updateTagRequest: UpdateTagRequest, tagId: String): TagResponse {
        val tagEntity = tagRepository.findById(tagId)
            .orElseThrow { throw TagNotFoundException("Tag with id: ${tagId} not found") }
        tagMapper.mergeUpdateTagRequestToTagEntity(updateTagRequest, tagEntity)
        val savedEntity = tagRepository.save(tagEntity)
        return tagMapper.mapEntityToTagResponse(savedEntity)
    }

    @Transactional
    fun deleteTag(id: String) {
        if (!tagRepository.existsById(id)) {
            throw TagNotFoundException("Tag with id: $id not found")
        }
        tagRepository.deleteById(id)
        kafkaProducer.sendTagDeletedMessage(id)
    }
}