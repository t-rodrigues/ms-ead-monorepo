package dev.trodrigues.ead.authuser.controllers.requests

data class PutUserRequest(
    val fullName: String?,
    val phoneNumber: String?,
    val cpf: String?
)
