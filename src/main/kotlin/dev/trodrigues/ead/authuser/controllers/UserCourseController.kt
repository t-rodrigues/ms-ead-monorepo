package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.clients.CourseClient
import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.extension.toPageResponse
import dev.trodrigues.ead.authuser.services.UserService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class UserCourseController(
    private val userService: UserService,
    private val courseClient: CourseClient
) {

    @GetMapping("/users/{userId}/courses")
    // @Retry(name = "retryInstance", fallbackMethod = "retryFallback")
    @CircuitBreaker(name = "circuitbreakerInstance")
    fun getCoursesByUser(
        @PathVariable userId: UUID,
        @PageableDefault(size = 10, sort = ["creationDate"]) pageable: Pageable
    ): PageResponse<CourseResponse> {
        val user = userService.findById(userId)
        return courseClient.getCoursesByUser(user.id!!, pageable)
    }

    fun circuitbreakerFallback(userId: UUID, pageable: Pageable, throwable: Throwable): PageResponse<CourseResponse> {
        return PageImpl(mutableListOf<CourseResponse>()).toPageResponse()
    }

    fun retryFallback(userId: UUID, pageable: Pageable, throwable: Throwable): PageResponse<CourseResponse> {
        return PageImpl(mutableListOf<CourseResponse>()).toPageResponse()
    }

}
