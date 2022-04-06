package dev.trodrigues.ead.authuser.controllers.requests

import javax.validation.constraints.NotBlank

data class PatchUserAvatarRequest(
    @field:NotBlank
    val imageUrl: String?
)
