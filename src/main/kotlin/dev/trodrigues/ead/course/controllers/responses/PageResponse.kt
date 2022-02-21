package dev.trodrigues.ead.course.controllers.responses

data class PageResponse<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Long
)
