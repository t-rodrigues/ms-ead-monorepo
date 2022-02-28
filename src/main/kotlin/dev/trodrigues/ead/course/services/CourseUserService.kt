package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.CourseUserModel
import org.springframework.data.domain.Pageable
import java.util.*

interface CourseUserService {

    fun getUsersByCourse(courseId: UUID, pageable: Pageable): PageResponse<UserResponse>

    fun saveSubscriptionUserInCourse(course: CourseModel, userId: UUID): CourseUserModel

}
