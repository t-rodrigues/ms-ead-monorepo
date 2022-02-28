package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.SubscriptionRequest
import dev.trodrigues.ead.course.controllers.responses.CourseUserResponse
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.CourseService
import dev.trodrigues.ead.course.services.CourseUserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses/{courseId}/users")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class CourseUserController(
    private val courseUserService: CourseUserService,
    private val courseService: CourseService
) {

    @GetMapping
    fun getUsersByCourse(
        @PathVariable courseId: UUID,
        @PageableDefault(size = 10, sort = ["fullName"]) pageable: Pageable
    ): PageResponse<UserResponse> {
        return courseUserService.getUsersByCourse(courseId, pageable)
    }

    @PostMapping("/subscription")
    fun saveSubscriptionUserInCourse(
        @PathVariable courseId: UUID,
        @Valid @RequestBody subscriptionRequest: SubscriptionRequest
    ): CourseUserResponse {
        val course = courseService.getCourseById(courseId)
        println(subscriptionRequest)
        val courseUser = courseUserService.saveSubscriptionUserInCourse(course, subscriptionRequest.userId!!)
        return courseUser.toResponse()
    }

}
