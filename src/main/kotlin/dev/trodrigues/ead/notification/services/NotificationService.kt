package dev.trodrigues.ead.notification.services

import dev.trodrigues.ead.notification.models.NotificationModel

interface NotificationService {

    fun saveNotification(notificationModel: NotificationModel): NotificationModel

}
