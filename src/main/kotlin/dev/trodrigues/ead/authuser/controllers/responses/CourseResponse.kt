package dev.trodrigues.ead.authuser.controllers.responses

import dev.trodrigues.ead.authuser.enums.CourseLevel
import dev.trodrigues.ead.authuser.enums.CourseStatus
import java.util.*

data class CourseResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val courseLevel: CourseLevel,
    val courseStatus: CourseStatus,
    val userInstructor: UUID
)
