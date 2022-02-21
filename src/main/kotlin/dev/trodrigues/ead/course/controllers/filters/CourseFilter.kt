package dev.trodrigues.ead.course.controllers.filters

import dev.trodrigues.ead.course.enums.CourseLevel
import dev.trodrigues.ead.course.enums.CourseStatus

data class CourseFilter(
    val name: String?,
    val courseStatus: CourseStatus?,
    val courseLevel: CourseLevel?
)
