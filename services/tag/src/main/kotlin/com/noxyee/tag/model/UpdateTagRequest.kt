package com.noxyee.tag.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class UpdateTagRequest(@field:NotBlank val name: String?, @field:Pattern(regexp = "^#([A-Fa-f0-9]{6})$") val color: String?)