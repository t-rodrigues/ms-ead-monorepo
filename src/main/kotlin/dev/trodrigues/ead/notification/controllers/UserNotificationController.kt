package dev.trodrigues.ead.notification.controllers

import dev.trodrigues.ead.notification.controllers.responses.NotificationResponse
import dev.trodrigues.ead.notification.controllers.responses.PageResponse
import dev.trodrigues.ead.notification.extension.toPageResponse
import dev.trodrigues.ead.notification.extension.toResponse
import dev.trodrigues.ead.notification.services.NotificationService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users/{userId}/notifications")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class UserNotificationController(
    private val notificationService: NotificationService
) {

    @GetMapping
    fun getNotificationsByUser(
        @PathVariable userId: UUID,
        @PageableDefault(
            page = 0,
            size = 15,
            sort = ["creationDate"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): PageResponse<NotificationResponse> {
        val notifications = notificationService.getNotificationsByUser(userId, pageable)
        return notifications.map { it.toResponse() }.toPageResponse()
    }

}
