package dev.trodrigues.ead.authuser.controllers.responses

import java.util.*

data class UserEventResponse(
    val id: UUID,
    val username: String,
    val fullName: String,
    val userStatus: String,
    val userType: String,
    val actionType: String,
    val phoneNumber: String?,
    val cpf: String?,
    val imageUrl: String?
)
