package dev.trodrigues.ead.course.controllers.responses

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
    val creationDate: LocalDateTime? = null,
    val lastUpdateDate: LocalDateTime? = null

)
