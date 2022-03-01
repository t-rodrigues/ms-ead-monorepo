package dev.trodrigues.ead.serviceregister

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceRegisterApplication

fun main(args: Array<String>) {
    runApplication<ServiceRegisterApplication>(*args)
}
