package dev.trodrigues.ead.notification.controllers.requests

import dev.trodrigues.ead.notification.enums.NotificationStatus
import javax.validation.constraints.NotNull

data class NotificationPatchRequest(
    @field:NotNull
    val notificationStatus: NotificationStatus?
)
