package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.repositories.UserCourseRepository
import dev.trodrigues.ead.authuser.services.UserCourseService
import org.springframework.stereotype.Service

@Service
class UserCourseServiceImpl(
    private val userCourseRepository: UserCourseRepository
) : UserCourseService
