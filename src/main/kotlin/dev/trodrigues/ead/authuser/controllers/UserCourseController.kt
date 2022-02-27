package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.requests.PostUserCourseRequest
import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.controllers.responses.UserCourseResponse
import dev.trodrigues.ead.authuser.extension.toResponse
import dev.trodrigues.ead.authuser.services.UserCourseService
import dev.trodrigues.ead.authuser.services.UserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/users/{userId}/courses")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class UserCourseController(
    private val userCourseService: UserCourseService,
    private val userService: UserService
) {

    @GetMapping
    fun getCoursesByUser(
        @PathVariable userId: UUID,
        @PageableDefault(size = 10, sort = ["creationDate"]) pageable: Pageable
    ): PageResponse<CourseResponse> {
        return userCourseService.getCoursesByUser(userId, pageable)
    }

    @PostMapping("/subscription")
    fun saveSubscriptionUserInCourse(
        @PathVariable userId: UUID,
        @Valid @RequestBody postUserCourseRequest: PostUserCourseRequest
    ): UserCourseResponse {
        val user = userService.findById(userId)
        val userCourse = userCourseService.saveSubscriptionUserInCourse(user, postUserCourseRequest.courseId!!)
        return userCourse.toResponse()
    }

}
