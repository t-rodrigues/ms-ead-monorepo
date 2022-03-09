package dev.trodrigues.ead.notification.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.trodrigues.ead.notification.consumers.responses.NotificationCommand
import dev.trodrigues.ead.notification.enums.NotificationStatus
import dev.trodrigues.ead.notification.extension.toModel
import dev.trodrigues.ead.notification.services.NotificationService
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class NotificationConsumer(
    private val notificationService: NotificationService,
    private val objectMapper: ObjectMapper
) {

    @RabbitListener(
        bindings = [QueueBinding(
            value = Queue(
                value = "\${ead.broker.queue.notificationCommandQueue.name}",
                durable = "true"
            ),
            exchange = Exchange(
                value = "\${ead.broker.exchange.notificationCommandExchange}",
                type = ExchangeTypes.TOPIC
            ),
            key = ["\${ead.broker.key.notificationCommandKey}"]
        )
        ]
    )
    fun listen(@Payload message: Message) {
        val notificationCommand = objectMapper.readValue(message.body, NotificationCommand::class.java)
        notificationService.saveNotification(notificationCommand.toModel(NotificationStatus.CREATED))
    }

}
