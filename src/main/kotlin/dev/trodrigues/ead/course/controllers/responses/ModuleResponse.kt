package dev.trodrigues.ead.course.controllers.responses

import java.time.LocalDateTime
import java.util.*

data class ModuleResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val creationDate: LocalDateTime
)
