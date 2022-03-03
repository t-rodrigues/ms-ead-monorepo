package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.course.clients.AuthUserClient
import dev.trodrigues.ead.course.controllers.requests.PostUserCourseRequest
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.CourseUserModel
import dev.trodrigues.ead.course.repositories.CourseUserRepository
import dev.trodrigues.ead.course.services.CourseUserService
import dev.trodrigues.ead.course.services.exceptions.ConflictException
import dev.trodrigues.ead.course.services.exceptions.ForbiddenException
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import feign.FeignException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CourseUserServiceImpl(
    private val courseUserRepository: CourseUserRepository,
    private val authUserClient: AuthUserClient
) : CourseUserService {

    override fun getUsersByCourse(courseId: UUID, pageable: Pageable): PageResponse<UserResponse> {
        return authUserClient.getUsersByCourse(courseId, pageable)
    }

    @Transactional
    override fun saveSubscriptionUserInCourse(course: CourseModel, userId: UUID): CourseUserModel {
        if (courseUserRepository.existsByCourseAndUserId(course, userId)) {
            throw ConflictException("subscription already exists")
        }
        try {
            val userResponse = authUserClient.getUserById(userId)
            if (userResponse.userStatus == UserStatus.BLOCKED) {
                throw ForbiddenException("User is blocked")
            }
            val courseUser = CourseUserModel(course = course, userId = userResponse.id!!)
            authUserClient.postSubscriptionUserInCourse(
                courseUser.userId,
                PostUserCourseRequest(courseUser.course.id!!)
            )
            return courseUserRepository.save(courseUser)
        } catch (ex: FeignException) {
            throw NotFoundException("User not found")
        }
    }

    @Transactional
    override fun deleteCourseUserByUser(userId: UUID) {
        if (!courseUserRepository.existsByUserId(userId)) {
            throw NotFoundException("User not found: [$userId]")
        }
        courseUserRepository.deleteAllByUserId(userId)
    }

}
