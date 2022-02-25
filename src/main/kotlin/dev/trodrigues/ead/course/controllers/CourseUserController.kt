package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.services.CourseUserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class CourseUserController(
    private val courseUserService: CourseUserService
) {

    @GetMapping("/courses/{courseId}/users")
    fun getUsersByCourse(
        @PathVariable courseId: UUID,
        @PageableDefault(size = 10, sort = ["fullName"]) pageable: Pageable
    ): PageResponse<UserResponse> {
        return courseUserService.getUsersByCourse(courseId, pageable)
    }

}
