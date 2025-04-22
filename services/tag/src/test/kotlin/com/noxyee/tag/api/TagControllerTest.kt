package com.noxyee.tag.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.noxyee.tag.BaseIntegration
import com.noxyee.tag.model.CreateTagRequest
import com.noxyee.tag.model.UpdateTagRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "user1", roles = ["USER"])
class TagControllerTest : BaseIntegration() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `getTags should return three tags for user with tags`() {
        // Given
        val userId = "09eff104-d734-4729-8d26-b4782b4e2f69"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tags/$userId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(3))
    }

    @Test
    fun `getTags should return zero tags for user without tags`() {
        // Given
        val userId = "invalid-user-id"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tags/$userId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(0))
    }

    @Test
    fun `createTag should create a new tag`() {
        // Given
        val createTagRequest = CreateTagRequest(
            name = "New Tag",
            color = "#FF5733",
            userId = "09eff104-d734-4729-8d26-b4782b4e2f61"
        )

        // When - then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createTagRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("New Tag"))
            .andExpect(jsonPath("$.color").value("#FF5733"))
            .andExpect(jsonPath("$.userId").value("09eff104-d734-4729-8d26-b4782b4e2f61"))
    }

    @Test
    fun `createTag should return error with three errors`() {
        // Given
        val createTagRequest = CreateTagRequest(
            name = "",
            color = "",
            userId = ""
        )

        //When - then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createTagRequest))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.size()").value(1))
    }

    @Test
    fun `updateTag should update tag name`() {
        // Given
        val tagId = "5d96834e-ae64-4b65-a7a7-829e9988c8e9"
        val newTagName = "Test Tag Updated"
        val updateTagRequest = UpdateTagRequest(
            name = newTagName,
            color = null
        )

        //When - then
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/tags/{tagId}", tagId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(updateTagRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(newTagName))
    }

    @Test
    fun `updateTag should return not found when tag id does not exists`() {
        // Given
        val tagId = "invalid-tag-id"
        val updateTagRequest = UpdateTagRequest(
            name = "newTagName",
            color = "#123456"
        )

        // When
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/tags/{tagId}", tagId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(updateTagRequest))
        )
            .andExpect(status().isNotFound)
            .andExpect(content().string("Tag with id: invalid-tag-id not found"))
    }

    @Test
    fun `deleteTag should delete tag`() {
        // Given
        val tagId = "28466895-0291-4389-8803-e77b9b91edcf"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tags/$tagId"))
            .andExpect { status().isOk }
    }

    @Test
    fun `deleteTag should return tag not found`() {
        // Given
        val tagId = "invalid-tag-id"

        // When - then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/v1/tags/{tagId}", tagId)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(content().string("Tag with id: invalid-tag-id not found"))

    }
}