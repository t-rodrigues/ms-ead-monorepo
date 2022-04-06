package dev.trodrigues.ead.course.publishers.responses

import java.util.*

data class NotificationCommand(
    val title: String,
    val message: String,
    val userId: UUID
)
