package dev.trodrigues.ead.authuser.controllers.requests

data class PatchPasswordRequest(
    val password: String,
    val oldPassword: String
)
