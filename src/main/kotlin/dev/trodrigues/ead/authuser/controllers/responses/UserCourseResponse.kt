package dev.trodrigues.ead.authuser.controllers.responses

import java.util.*

data class UserCourseResponse(
    val id: UUID,
    val user: UserResponse,
    val courseId: UUID,
)
