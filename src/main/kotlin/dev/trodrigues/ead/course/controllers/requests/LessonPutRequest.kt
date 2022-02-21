package dev.trodrigues.ead.course.controllers.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class LessonPutRequest(
    @field:NotBlank
    @field:Size(max = 150)
    val title: String?,
    @field:Size(max = 250)
    val description: String?,
    @field:NotBlank
    @field:Size(max = 250)
    val videoUrl: String?
)
