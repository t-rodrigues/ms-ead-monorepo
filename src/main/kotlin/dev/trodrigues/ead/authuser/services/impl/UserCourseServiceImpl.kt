package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.clients.CourseClient
import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.repositories.UserCourseRepository
import dev.trodrigues.ead.authuser.services.UserCourseService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCourseServiceImpl(
    private val userCourseRepository: UserCourseRepository,
    private val courseClient: CourseClient
) : UserCourseService {

    override fun getCoursesByUser(userId: UUID, pageable: Pageable): PageResponse<CourseResponse> {
        val response = courseClient.getCoursesByUser(userId, pageable)
        println(response.items)
        return response
    }

}
