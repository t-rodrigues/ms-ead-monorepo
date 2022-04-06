package dev.trodrigues.ead.course.consumers.responses

import java.util.*

data class UserEvent(
    val id: UUID,
    val username: String,
    val fullName: String,
    val email: String,
    val userStatus: String,
    val userType: String,
    val actionType: String,
    val phoneNumber: String?,
    val cpf: String?,
    val imageUrl: String?
)
