package dev.trodrigues.ead.course.controllers.requests

import dev.trodrigues.ead.course.enums.CourseLevel
import dev.trodrigues.ead.course.enums.CourseStatus
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CoursePutRequest(
    @field:NotBlank
    @field:Size(max = 150)
    val name: String?,
    @field:NotBlank
    @field:Size(max = 250)
    val description: String?,
    @field:NotNull
    val courseStatus: CourseStatus?,
    @field:NotNull
    val courseLevel: CourseLevel?
)
