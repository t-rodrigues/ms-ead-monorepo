package dev.trodrigues.ead.authuser.controllers.requests

import javax.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank
    val username: String?,
    @field:NotBlank
    val password: String?
)
