package dev.trodrigues.ead.notification.services.impl

import dev.trodrigues.ead.notification.enums.NotificationStatus
import dev.trodrigues.ead.notification.models.NotificationModel
import dev.trodrigues.ead.notification.repositories.NotificationRepository
import dev.trodrigues.ead.notification.services.NotificationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository
) : NotificationService {

    override fun saveNotification(notificationModel: NotificationModel): NotificationModel =
        notificationRepository.save(notificationModel)

    override fun getNotificationsByUser(userId: UUID, pageable: Pageable): Page<NotificationModel> =
        notificationRepository.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageable)

}
