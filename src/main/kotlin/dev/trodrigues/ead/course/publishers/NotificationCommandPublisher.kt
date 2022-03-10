package dev.trodrigues.ead.course.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.trodrigues.ead.course.publishers.responses.NotificationCommand
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class NotificationCommandPublisher(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) {

    @Value("\${ead.broker.exchange.notificationCommandExchange}")
    private val notificationCommandExchange: String? = null

    @Value("\${ead.broker.key.notificationCommandKey}")
    private val notificationCommandKey: String? = null

    fun publishNotificationCommand(notificationCommand: NotificationCommand) {
        rabbitTemplate.convertAndSend(
            notificationCommandExchange!!,
            notificationCommandKey!!,
            objectMapper.writeValueAsString(notificationCommand)
        )
    }

}
