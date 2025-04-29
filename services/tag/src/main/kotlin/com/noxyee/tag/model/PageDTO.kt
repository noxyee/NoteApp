package com.noxyee.tag.model

import org.springframework.data.domain.Page

data class PageDTO<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Long
) {

    companion object {
        fun <T> fromPage(page: Page<T>): PageDTO<T> = PageDTO(
            content = page.content,
            page = page.number,
            size = page.size,
            totalElements = page.totalElements
        )
    }
}
