package dev.trodrigues.ead.authuser.controllers.requests

data class PostUserRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val cpf: String
)
