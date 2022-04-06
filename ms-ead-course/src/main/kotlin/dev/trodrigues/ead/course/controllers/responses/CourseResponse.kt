package dev.trodrigues.ead.course.controllers.responses

import com.fasterxml.jackson.annotation.JsonFormat
import dev.trodrigues.ead.course.enums.CourseLevel
import dev.trodrigues.ead.course.enums.CourseStatus
import java.time.LocalDateTime
import java.util.*

data class CourseResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val courseStatus: CourseStatus,
    val courseLevel: CourseLevel,
    val userInstructor: UUID,
    val imageUrl: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val creationDate: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val lastUpdateDate: LocalDateTime? = null
)
