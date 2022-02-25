package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.clients.CourseClient
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.repositories.CourseUserRepository
import dev.trodrigues.ead.course.services.CourseUserService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseUserServiceImpl(
    private val courseUserRepository: CourseUserRepository,
    private val courseClient: CourseClient
) : CourseUserService {

    override fun getUsersByCourse(courseId: UUID, pageable: Pageable): PageResponse<UserResponse> {
        return courseClient.getUsersByCourse(courseId, pageable)
    }

}
