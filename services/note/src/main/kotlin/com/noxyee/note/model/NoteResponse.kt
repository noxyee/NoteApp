package com.noxyee.note.model

data class NoteResponse(
    val id: String,
    val title: String?,
    val content: String?,
    val userId: String,
    val tagIds: Set<String>?,
    val taskIds: Set<String>?,
    val imageIds: List<String>?
)
