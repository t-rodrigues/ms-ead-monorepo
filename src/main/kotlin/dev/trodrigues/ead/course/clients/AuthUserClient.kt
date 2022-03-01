package dev.trodrigues.ead.course.clients

import dev.trodrigues.ead.course.controllers.requests.PostUserCourseRequest
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@FeignClient(name = "ead-auth-user-service")
interface AuthUserClient {

    @GetMapping("/users")
    fun getUsersByCourse(@RequestParam courseId: UUID, pageable: Pageable): PageResponse<UserResponse>

    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: UUID): UserResponse

    @PostMapping("/users/{userId}/courses/subscription")
    fun postSubscriptionUserInCourse(@PathVariable userId: UUID, @RequestBody postUserCourseRequest: PostUserCourseRequest)

}
