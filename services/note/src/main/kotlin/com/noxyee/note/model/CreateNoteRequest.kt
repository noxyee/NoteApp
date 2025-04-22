package com.noxyee.note.model

import org.hibernate.validator.constraints.Length

data class CreateNoteRequest(
    @field:Length(min = 36, max = 36) val userId: String
)