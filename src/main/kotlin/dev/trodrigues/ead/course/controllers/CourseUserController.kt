package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.filters.UserFilter
import dev.trodrigues.ead.course.controllers.requests.SubscriptionRequest
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.extension.toPageResponse
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.UserService
import dev.trodrigues.ead.course.specifications.UserSpec
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses/{courseId}/users")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class CourseUserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsersByCourse(
        filter: UserFilter,
        @PathVariable courseId: UUID,
        @PageableDefault(size = 10, sort = ["fullName"]) pageable: Pageable
    ): PageResponse<UserResponse> {
        val users = userService.getCoursesByUser(UserSpec.getUsers(filter, courseId), pageable)
        return users.map { it.toResponse() }.toPageResponse()
    }

    @PostMapping("/subscription")
    fun saveSubscriptionUserInCourse(
        @PathVariable courseId: UUID,
        @Valid @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
//        val course = courseService.getCourseById(courseId)
//        val courseUser = courseUserService.saveSubscriptionUserInCourse(course, subscriptionRequest.userId!!)
    }

}
