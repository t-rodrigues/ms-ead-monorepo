package dev.trodrigues.ead.course.controllers.requests

import java.util.*
import javax.validation.constraints.NotNull

data class SubscriptionRequest(
    @field:NotNull
    val userId: UUID?
)
