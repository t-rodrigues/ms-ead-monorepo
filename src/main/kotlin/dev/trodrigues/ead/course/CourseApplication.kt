package dev.trodrigues.ead.course

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
class CourseApplication

fun main(args: Array<String>) {
    runApplication<CourseApplication>(*args)
}
