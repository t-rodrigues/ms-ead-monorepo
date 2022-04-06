package dev.trodrigues.ead.authuser.controllers.filters

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType

data class UserFilter(
    val userType: UserType?,
    val userStatus: UserStatus?,
    val email: String?,
    val fullName: String?
)
