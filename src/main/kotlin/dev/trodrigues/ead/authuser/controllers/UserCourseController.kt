package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.services.UserCourseService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class UserCourseController(
    private val userCourseService: UserCourseService
) {

    @GetMapping("/users/{userId}/courses")
    fun getCoursesByUser(
        @PathVariable userId: UUID,
        @PageableDefault(size = 10, sort = ["creationDate"]) pageable: Pageable
    ): PageResponse<CourseResponse> {
        return userCourseService.getCoursesByUser(userId, pageable)
    }

}
