package dev.trodrigues.ead.notification.services.impl

import dev.trodrigues.ead.notification.models.NotificationModel
import dev.trodrigues.ead.notification.repositories.NotificationRepository
import dev.trodrigues.ead.notification.services.NotificationService
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository
) : NotificationService {

    override fun saveNotification(notificationModel: NotificationModel): NotificationModel =
        notificationRepository.save(notificationModel)

}
