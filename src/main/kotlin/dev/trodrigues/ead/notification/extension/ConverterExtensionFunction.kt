package dev.trodrigues.ead.notification.extension

import dev.trodrigues.ead.notification.consumers.responses.NotificationCommand
import dev.trodrigues.ead.notification.enums.NotificationStatus
import dev.trodrigues.ead.notification.models.NotificationModel

fun NotificationCommand.toModel(notificationStatus: NotificationStatus): NotificationModel = NotificationModel(
    userId = this.userId,
    title = this.title,
    message = this.message,
    notificationStatus = notificationStatus
)
