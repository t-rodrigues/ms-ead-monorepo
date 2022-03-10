package dev.trodrigues.ead.notification.controllers.responses

import com.fasterxml.jackson.annotation.JsonFormat
import dev.trodrigues.ead.notification.enums.NotificationStatus
import java.time.LocalDateTime
import java.util.*

data class NotificationResponse(
    val id: UUID,
    val userId: UUID,
    val title: String,
    val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val creationDate: LocalDateTime,
    val notificationStatus: NotificationStatus
)
