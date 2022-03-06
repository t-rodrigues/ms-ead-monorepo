package dev.trodrigues.ead.course.configs

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfig {

    @Value("\${ead.broker.queue.userEventQueue.name}")
    private val queueUserEvent: String? = null

    @Bean
    fun queueUserEvent(): Queue = Queue(queueUserEvent, true)

}
