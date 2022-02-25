package dev.trodrigues.ead.course

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CourseApplication

fun main(args: Array<String>) {
    runApplication<CourseApplication>(*args)
}
