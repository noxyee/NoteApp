package com.noxyee.tag.util

import com.noxyee.tag.dao.TagEntity
import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.TagResponse
import com.noxyee.tag.model.UpdateTagRequest
import org.springframework.stereotype.Component

@Component
class TagMapper {

    fun mapCreateNoteRequestToTagEntity(createTagRequest: CreateTagRequest): TagEntity {
        return TagEntity(
            name = createTagRequest.name,
            color = createTagRequest.color,
            userId = createTagRequest.userId
        )
    }

    fun mapEntityToTagResponse(savedEntity: TagEntity): TagResponse {
        return TagResponse(
            id = savedEntity.id!!,
            name = savedEntity.name,
            color = savedEntity.color,
            userId = savedEntity.userId
        )
    }

    fun mergeUpdateTagRequestToTagEntity(updateTagRequest: UpdateTagRequest, tagEntity: TagEntity) {
        tagEntity.apply {
            name = updateTagRequest.name ?: tagEntity.name
            color = updateTagRequest.color ?: tagEntity.color
        }
    }
}