package dev.trodrigues.ead.authuser.controllers.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PatchPasswordRequest(
    @field:NotBlank
    @field:Size(min = 6)
    val password: String,

    @field:NotBlank
    val oldPassword: String
)
