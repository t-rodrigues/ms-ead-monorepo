package dev.trodrigues.ead.authuser.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.trodrigues.ead.authuser.controllers.responses.UserEventResponse
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class UserEventPublisher(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper,
    private val queueUserEvent: Queue
) {

    fun publishUserEvent(userEventResponse: UserEventResponse) {
        rabbitTemplate.convertAndSend(queueUserEvent.name, "", objectMapper.writeValueAsString(userEventResponse))
    }

}
