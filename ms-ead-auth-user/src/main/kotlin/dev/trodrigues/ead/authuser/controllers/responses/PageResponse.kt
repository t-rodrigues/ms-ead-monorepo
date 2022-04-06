package dev.trodrigues.ead.authuser.controllers.responses

data class PageResponse<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Long
)
