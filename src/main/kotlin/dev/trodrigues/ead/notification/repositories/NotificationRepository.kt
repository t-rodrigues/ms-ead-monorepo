package dev.trodrigues.ead.notification.repositories

import dev.trodrigues.ead.notification.models.NotificationModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface NotificationRepository : JpaRepository<NotificationModel, UUID>
