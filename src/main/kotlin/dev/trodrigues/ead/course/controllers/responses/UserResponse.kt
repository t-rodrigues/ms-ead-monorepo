package dev.trodrigues.ead.course.controllers.responses

import dev.trodrigues.ead.course.enums.UserStatus
import dev.trodrigues.ead.course.enums.UserType
import java.util.*

data class UserResponse(
    val id: UUID,
    val fullName: String,
    val email: String,
    val userStatus: UserStatus,
    val userType: UserType,
    val cpf: String? = null,
    val imageUrl: String? = null
)
