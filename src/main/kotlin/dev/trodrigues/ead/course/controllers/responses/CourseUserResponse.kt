package dev.trodrigues.ead.course.controllers.responses

import java.util.*

data class CourseUserResponse(
    val id: UUID,
    val courseId: UUID,
    val userId: UUID
)
