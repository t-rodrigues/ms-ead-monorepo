package dev.trodrigues.ead.authuser.controllers.requests.filters

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import java.util.UUID

data class UserFilter(
    val courseId: UUID?,
    val userType: UserType?,
    val userStatus: UserStatus?,
    val email: String?,
    val fullName: String?
)
