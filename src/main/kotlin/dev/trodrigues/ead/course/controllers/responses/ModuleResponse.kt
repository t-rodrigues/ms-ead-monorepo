package dev.trodrigues.ead.course.controllers.responses

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

data class ModuleResponse(
    val id: UUID,
    val title: String,
    val description: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val creationDate: LocalDateTime
)
