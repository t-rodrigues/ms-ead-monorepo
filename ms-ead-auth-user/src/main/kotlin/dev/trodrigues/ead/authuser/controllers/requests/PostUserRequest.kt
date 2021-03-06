package dev.trodrigues.ead.authuser.controllers.requests

import dev.trodrigues.ead.authuser.validation.UsernameConstraint
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class PostUserRequest(
    @field:NotBlank
    @field:UsernameConstraint
    val username: String?,

    @field:NotBlank
    @field:Email
    val email: String?,

    @field:NotBlank
    @field:Size(min = 6)
    val password: String?,

    val fullName: String?,
    val phoneNumber: String?,
    val cpf: String?
)
