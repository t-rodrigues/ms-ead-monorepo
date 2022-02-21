package dev.trodrigues.ead.course.controllers.responses

import java.time.LocalDateTime
import java.util.*

data class LessonResponse(
    val id: UUID,
    val title: String,
    val description: String? = null,
    val creationDate: LocalDateTime
)
