package dev.trodrigues.ead.course.controllers.filters

import dev.trodrigues.ead.course.enums.UserStatus
import dev.trodrigues.ead.course.enums.UserType

data class UserFilter(
    val email: String?,
    val fullName: String?,
    val userStatus: UserStatus?,
    val userType: UserType?
)
