package dev.trodrigues.ead.course.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.trodrigues.ead.course.controllers.responses.UserEvent
import dev.trodrigues.ead.course.enums.ActionType
import dev.trodrigues.ead.course.extension.toModel
import dev.trodrigues.ead.course.services.UserService
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class UserConsumer(
    private val userService: UserService,
    private val objectMapper: ObjectMapper
) {

    @RabbitListener(
        bindings = [QueueBinding(
            value = Queue(
                value = "\${ead.broker.queue.userEventQueue.name}",
                durable = "true"
            ),
            exchange = Exchange(
                value = "\${ead.broker.exchange.userEvent}",
                type = ExchangeTypes.FANOUT,
                ignoreDeclarationExceptions = "true"
            )
        )]
    )
    fun listenUserEvent(@Payload message: Message) {
        val userEvent = objectMapper.readValue(message.body, UserEvent::class.java)
        when (ActionType.valueOf(userEvent.actionType.uppercase())) {
            ActionType.CREATE -> userService.registerUser(userEvent.toModel())
            ActionType.UPDATE -> userService.updateUser(userEvent.toModel())
            ActionType.DELETE -> userService.deleteUser(userEvent.id)
        }
    }

}
