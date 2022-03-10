package dev.trodrigues.ead.notification.repositories

import dev.trodrigues.ead.notification.enums.NotificationStatus
import dev.trodrigues.ead.notification.models.NotificationModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface NotificationRepository : JpaRepository<NotificationModel, UUID> {

    fun findAllByUserIdAndNotificationStatus(
        userId: UUID,
        notificationStatus: NotificationStatus,
        pageable: Pageable
    ): Page<NotificationModel>

}
