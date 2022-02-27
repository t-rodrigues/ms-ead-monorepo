package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.clients.CourseClient
import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.models.UserCourseModel
import dev.trodrigues.ead.authuser.models.UserModel
import dev.trodrigues.ead.authuser.repositories.UserCourseRepository
import dev.trodrigues.ead.authuser.services.UserCourseService
import dev.trodrigues.ead.authuser.services.exceptions.ConflictException
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

    override fun saveSubscriptionUserInCourse(user: UserModel, courseId: UUID): UserCourseModel {
        if (userCourseRepository.existsByUserAndCourseId(user, courseId)) {
            throw ConflictException("subscription already exists")
        }
        val userCourse = UserCourseModel(user = user, courseId = courseId)
        return userCourseRepository.save(userCourse)
    }

}
