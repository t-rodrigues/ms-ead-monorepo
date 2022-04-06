package dev.trodrigues.ead.authuser.controllers.responses

data class JwtResponse(
    val accessToken: String,
    val type: String = "Bearer"
)
