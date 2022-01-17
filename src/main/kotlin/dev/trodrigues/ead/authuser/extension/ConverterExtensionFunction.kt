package dev.trodrigues.ead.authuser.extension

import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.models.UserModel

fun UserModel.toResponse(): UserResponse = UserResponse(
    id = this.id,
    username = this.username,
    email = this.email,
    password = this.password,
    fullName = this.fullName,
    userStatus = this.userStatus,
    userType = this.userType,
    cpf = this.cpf
)