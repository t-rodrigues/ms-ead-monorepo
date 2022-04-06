package dev.trodrigues.ead.authuser.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.trodrigues.ead.authuser.controllers.responses.UserEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class UserEventPublisher(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) {
    @Value("\${ead.broker.exchange.userEvent}")
    val exchangeUserEvent: String? = null

    fun publishUserEvent(userEvent: UserEvent) {
        rabbitTemplate.convertAndSend(exchangeUserEvent!!, "", objectMapper.writeValueAsString(userEvent))
    }

}
