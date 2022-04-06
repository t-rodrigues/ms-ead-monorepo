package dev.trodrigues.ead.notification.controllers.responses

data class PageResponse<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Long
)
