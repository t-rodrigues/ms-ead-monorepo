package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import dev.trodrigues.ead.authuser.models.UserCourseModel
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.domain.Pageable
import java.util.*

interface UserCourseService {

    fun getCoursesByUser(userId: UUID, pageable: Pageable): PageResponse<CourseResponse>

    fun saveSubscriptionUserInCourse(user: UserModel, courseId: UUID): UserCourseModel

    fun deleteUserCourseByCourse(courseId: UUID)

}
