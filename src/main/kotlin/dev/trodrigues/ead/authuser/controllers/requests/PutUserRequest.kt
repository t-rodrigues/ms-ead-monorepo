package dev.trodrigues.ead.authuser.controllers.requests

data class PutUserRequest(
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val cpf: String? = null
)
