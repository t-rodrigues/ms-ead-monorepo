package dev.trodrigues.ead.notification.models

import dev.trodrigues.ead.notification.enums.NotificationStatus
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "tb_notifications")
data class NotificationModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val userId: UUID,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    val notificationStatus: NotificationStatus
)
