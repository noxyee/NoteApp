package com.noxyee.note.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.noxyee.note.BaseIntegration
import com.noxyee.note.client.TaskClient
import com.noxyee.note.dao.NoteRepository
import com.noxyee.note.model.CreateNoteRequest
import com.noxyee.note.model.UpdateNoteRequest
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "user1", roles = ["USER"])
class NoteControllerTest : BaseIntegration() {

    @MockitoBean
    private lateinit var taskClient: TaskClient

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var noteRepository: NoteRepository

    private val objectMapper = ObjectMapper()

    @Test
    fun `getUserNotes should return five notes for user with notes`() {
        // Given
        val userId = "09eff104-d734-4729-8d26-b4782b4e2f69"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notes/$userId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content.length()").value(5))
            .andExpect(jsonPath("$.totalElements").value(5))
    }

    @Test
    fun `getUserNotes should return 0 notes for user with no notes`() {
        // Given
        val userId = "00000000-0000-0000-0000-000000000000"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notes/$userId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content.length()").value(0))
            .andExpect(jsonPath("$.totalElements").value(0))
            .andExpect(jsonPath("$.totalPages").value(0))
            .andExpect(jsonPath("$.number").value(0))
    }

    @Test
    fun `createNote should create a new note`() {
        // Given
        val userId = "71eff105-d75-6536-1d82-b47625b4e2f64"

        val createNoteRequest = CreateNoteRequest(userId)

        // When - then
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createNoteRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").isNotEmpty)
            .andExpect(jsonPath("$.title").value(null))
            .andExpect(jsonPath("$.content").value(null))
            .andReturn()

        val id = objectMapper.readTree(response.response.contentAsString).get("id").asText()
        Assertions.assertNotNull(noteRepository.findById(id).get())
    }

    @Test
    fun `createNote should return error when user id is missing`() {
        // Given
        val createNoteRequest = CreateNoteRequest("")

        // When - then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createNoteRequest))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `updateNote should update note content and title`() {
        // Given
        val first = noteRepository.findAll().first()
        val noteId = first.id
        val title = "New Title"
        val content = "New Content"

        val updateNoteRequest = UpdateNoteRequest(title, content)

        // When - then
        val response = mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/notes/$noteId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateNoteRequest))
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value(title))
            .andExpect(jsonPath("$.content").value(content))
            .andReturn()

        val id = objectMapper.readTree(response.response.contentAsString).get("id").asText()
        val note = noteRepository.findById(id).get()
        Assertions.assertEquals(title, note.title)
        Assertions.assertEquals(content, note.content)
    }

    @Test
    fun `updateNote should return not found note does not exist`() {
        // Given
        val noteId = "00000000-0000-0000-0000-000000000000"
        val title = "New Title"
        val content = "New Content"

        val updateNoteRequest = UpdateNoteRequest(title, content)

        // When - then
        val response = mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/notes/$noteId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateNoteRequest))
        )
            .andExpect(status().isNotFound)
            .andExpect(content().string("Note with id: 00000000-0000-0000-0000-000000000000 not found"))
    }

    @Test
    fun `deleteNote should delete tag`() {
        // Given
        val first = noteRepository.findAll().first()
        val noteId = first.id

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/notes/$noteId"))
            .andExpect(status().isOk)
    }

    @Test
    fun `deleteNote should return note not found when note does not exist`() {
        // Given
        val noteId = "00000000-0000-0000-0000-000000000000"

        // When - then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/notes/$noteId"))
            .andExpect(status().isNotFound)
            .andExpect(content().string("Note with id: 00000000-0000-0000-0000-000000000000 not found"))
    }
}