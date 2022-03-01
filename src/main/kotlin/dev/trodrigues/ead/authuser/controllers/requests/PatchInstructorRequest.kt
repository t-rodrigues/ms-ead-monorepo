package dev.trodrigues.ead.authuser.controllers.requests

import java.util.*
import javax.validation.constraints.NotNull

data class PatchInstructorRequest(
    @field:NotNull
    val userId: UUID?
)
