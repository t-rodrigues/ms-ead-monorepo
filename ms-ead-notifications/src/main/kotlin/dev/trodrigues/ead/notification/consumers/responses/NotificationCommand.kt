package dev.trodrigues.ead.notification.consumers.responses

import java.util.*

data class NotificationCommand(
    val title: String,
    val message: String,
    val userId: UUID
)
