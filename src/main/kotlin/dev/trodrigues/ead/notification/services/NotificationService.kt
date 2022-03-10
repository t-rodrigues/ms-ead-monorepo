package dev.trodrigues.ead.notification.services

import dev.trodrigues.ead.notification.models.NotificationModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface NotificationService {

    fun saveNotification(notificationModel: NotificationModel): NotificationModel

    fun getNotificationsByUser(userId: UUID, pageable: Pageable): Page<NotificationModel>

}
