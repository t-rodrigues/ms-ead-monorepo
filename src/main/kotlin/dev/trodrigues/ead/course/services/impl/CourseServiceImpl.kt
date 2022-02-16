package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.repositories.CourseRepository
import dev.trodrigues.ead.course.services.CourseService

class CourseServiceImpl(
    private val courseRepository: CourseRepository
) : CourseService {}