package dev.trodrigues.ead.authuser.controllers.filters

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import java.util.*

data class UserFilter(
    val courseId: UUID?,
    val userType: UserType?,
    val userStatus: UserStatus?,
    val email: String?,
    val fullName: String?
)
