package dev.trodrigues.ead.notification.extension

import dev.trodrigues.ead.notification.consumers.responses.NotificationCommand
import dev.trodrigues.ead.notification.controllers.responses.NotificationResponse
import dev.trodrigues.ead.notification.controllers.responses.PageResponse
import dev.trodrigues.ead.notification.enums.NotificationStatus
import dev.trodrigues.ead.notification.models.NotificationModel
import org.springframework.data.domain.Page

fun NotificationModel.toResponse(): NotificationResponse = NotificationResponse(
    id = this.id!!,
    userId = this.userId,
    title = this.title,
    message = this.message,
    creationDate = this.creationDate,
    notificationStatus = this.notificationStatus
)

fun NotificationCommand.toModel(notificationStatus: NotificationStatus): NotificationModel = NotificationModel(
    userId = this.userId,
    title = this.title,
    message = this.message,
    notificationStatus = notificationStatus
)

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse<T>(
    items = this.content,
    currentPage = this.number,
    totalPages = this.totalPages,
    totalItems = this.totalElements
)
