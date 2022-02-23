package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.repositories.CourseUserRepository
import dev.trodrigues.ead.course.services.CourseUserService
import org.springframework.stereotype.Service

@Service
class CourseUserServiceImpl(
    private val courseUserRepository: CourseUserRepository
) : CourseUserService
