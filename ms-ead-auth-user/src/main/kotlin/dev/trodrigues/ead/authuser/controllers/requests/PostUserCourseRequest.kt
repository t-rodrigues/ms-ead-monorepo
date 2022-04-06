package dev.trodrigues.ead.authuser.controllers.requests

import java.util.*
import javax.validation.constraints.NotNull

data class PostUserCourseRequest(
    @field:NotNull
    val courseId: UUID?
)
