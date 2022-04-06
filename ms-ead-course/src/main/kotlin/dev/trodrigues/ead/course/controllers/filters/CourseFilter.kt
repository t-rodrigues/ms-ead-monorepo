package dev.trodrigues.ead.course.controllers.filters

import dev.trodrigues.ead.course.enums.CourseLevel
import dev.trodrigues.ead.course.enums.CourseStatus
import java.util.*

data class CourseFilter(
    val name: String?,
    val courseStatus: CourseStatus?,
    val courseLevel: CourseLevel?,
    val userId: UUID?,
)
