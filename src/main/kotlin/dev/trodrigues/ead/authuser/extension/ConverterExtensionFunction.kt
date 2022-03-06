package dev.trodrigues.ead.authuser.extension

import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PatchUserAvatarRequest
import dev.trodrigues.ead.authuser.controllers.requests.PostUserRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.controllers.responses.UserEvent
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.enums.ActionType
import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.domain.Page
import java.time.LocalDateTime
import java.time.ZoneId

fun UserModel.toResponse(): UserResponse = UserResponse(
    id = this.id,
    username = this.username,
    email = this.email,
    fullName = this.fullName,
    userStatus = this.userStatus,
    userType = this.userType,
    cpf = this.cpf,
    imageUrl = this.imageUrl,
    phoneNumber = this.phoneNumber,
    creationDate = this.creationDate,
    lastUpdatedDate = this.lastUpdatedDate
)

fun UserModel.toUserEvent(actionType: ActionType) = UserEvent(
    id = this.id!!,
    username = this.username,
    email = this.email,
    fullName = this.fullName,
    userStatus = this.userStatus.name.uppercase(),
    userType = this.userType.name.uppercase(),
    actionType = actionType.name.uppercase(),
    phoneNumber = this.phoneNumber,
    cpf = this.cpf,
    imageUrl = this.imageUrl
)

fun PostUserRequest.toModel(): UserModel = UserModel(
    username = this.username!!,
    email = this.email!!,
    password = this.password!!,
    fullName = this.fullName!!,
    userStatus = UserStatus.ACTIVE,
    userType = UserType.STUDENT,
    cpf = this.cpf!!,
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
        password = this.password!!,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )

fun PatchUserAvatarRequest.toModel(previousValue: UserModel): UserModel =
    previousValue.copy(
        imageUrl = this.imageUrl,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse(
    items = this.content,
    currentPage = this.number,
    totalPages = this.totalPages,
    totalItems = this.totalElements
)
