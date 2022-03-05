package dev.trodrigues.ead.authuser.configs

import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfig {

    @Value("\${ead.broker.exchange.userEvent}")
    private val exchangeUserEvent: String? = null

    @Bean
    fun fanoutUserEvent(): FanoutExchange = FanoutExchange(exchangeUserEvent)

    @Bean
    fun queueUserEvent(): Queue = Queue(exchangeUserEvent, true)

}
