package com.noxyee.note.model

import org.hibernate.validator.constraints.Length

data class UpdateNoteRequest(
    @field:Length(max = 100) val title: String?,
    val content: String?,
)
