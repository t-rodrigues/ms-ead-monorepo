package dev.trodrigues.ead.authuser.extension

import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PatchUserAvatarRequest
import dev.trodrigues.ead.authuser.controllers.requests.PostUserRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.models.UserModel
import java.time.LocalDateTime
import java.time.ZoneId

fun UserModel.toResponse(): UserResponse = UserResponse(
    id = this.id,
    username = this.username,
    email = this.email,
    password = this.password,
    fullName = this.fullName,
    userStatus = this.userStatus,
    userType = this.userType,
    cpf = this.cpf,
    creationDate = this.creationDate,
    lastUpdatedDate = this.lastUpdatedDate
)

fun PostUserRequest.toModel(): UserModel = UserModel(
    username = this.username,
    email = this.email,
    password = this.password,
    fullName = this.fullName,
    userStatus = UserStatus.ACTIVE,
    userType = UserType.STUDENT,
    cpf = this.cpf,
    phoneNumber = phoneNumber
)

fun PutUserRequest.toModel(previousValue: UserModel): UserModel =
    previousValue.copy(
        cpf = this.cpf ?: previousValue.cpf,
        fullName = this.fullName ?: previousValue.fullName,
        phoneNumber = this.phoneNumber ?: previousValue.phoneNumber,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )

fun PatchPasswordRequest.toModel(previousValue: UserModel): UserModel =
    previousValue.copy(
        password = this.password,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )

fun PatchUserAvatarRequest.toModel(previousValue: UserModel): UserModel =
    previousValue.copy(
        imageUrl = this.imageUrl,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )
