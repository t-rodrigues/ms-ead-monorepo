package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import org.springframework.data.domain.Pageable
import java.util.*

interface CourseUserService {

    fun getUsersByCourse(courseId: UUID, pageable: Pageable): PageResponse<UserResponse>

}
