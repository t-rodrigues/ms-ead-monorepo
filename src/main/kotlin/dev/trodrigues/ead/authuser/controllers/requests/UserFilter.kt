package dev.trodrigues.ead.authuser.controllers.requests

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType

data class UserFilter(
    val userType: UserType? = null,
    val userStatus: UserStatus? = null,
    val email: String? = null
)
