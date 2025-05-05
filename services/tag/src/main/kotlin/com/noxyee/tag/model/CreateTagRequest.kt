package com.noxyee.tag.model

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class CreateTagRequest(
    @field:NotBlank @field:Length(max = 20) val name: String,
    @field:Length(min = 7, max = 7) val color: String = "#FFFFFF",
    @field:Length(min = 36, max = 36) val userId: String
)
