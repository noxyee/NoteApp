package com.noxyee.tag.dao

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.UuidGenerator
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime

@Entity
@Table(name = "tag")
data class TagEntity(
    @Id
    @UuidGenerator
    val id: String? = null,
    @NotBlank
    @Length(max = 20)
    var name: String,
    @Length(min = 7, max = 7)
    var color: String = "#FFFFFF",
    @Length(min = 36, max = 36)
    val userId: String,
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(name = "", userId = "")
}
