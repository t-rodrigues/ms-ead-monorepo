package dev.trodrigues.ead.authuser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class AuthUserApplication

fun main(args: Array<String>) {
    runApplication<AuthUserApplication>(*args)
}
