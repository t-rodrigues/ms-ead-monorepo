package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.clients.UserClient
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
    private val userClient: UserClient
) : UserCourseService {

    override fun getAllCoursesByUser(userId: UUID, pageable: Pageable): PageResponse<CourseResponse> {
        val response = userClient.getCoursesByUser(userId, pageable)
        println(response.items)
        return response
    }

}
