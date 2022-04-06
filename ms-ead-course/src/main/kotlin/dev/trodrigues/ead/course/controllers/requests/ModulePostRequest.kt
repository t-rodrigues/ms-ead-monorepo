package dev.trodrigues.ead.course.controllers.requests

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ModulePostRequest(
    @field:NotBlank
    @field:Size(max = 150)
    val title: String? = null,
    @field:NotBlank
    @field:Size(max = 250)
    val description: String? = null
)
