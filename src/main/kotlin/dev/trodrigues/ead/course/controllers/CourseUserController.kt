package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.SubscriptionRequest
import dev.trodrigues.ead.course.services.CourseService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class CourseUserController(
    private val courseService: CourseService
) {

    @GetMapping("/{courseId}/users")
    fun getUsersByCourse(
        @PathVariable courseId: UUID,
        @PageableDefault(size = 10, sort = ["fullName"]) pageable: Pageable
    ) {
//        return courseUserService.getUsersByCourse(courseId, pageable)
    }

    @PostMapping("/{courseId}/users/subscription")
    fun saveSubscriptionUserInCourse(
        @PathVariable courseId: UUID,
        @Valid @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        val course = courseService.getCourseById(courseId)
//        val courseUser = courseUserService.saveSubscriptionUserInCourse(course, subscriptionRequest.userId!!)
    }

}
