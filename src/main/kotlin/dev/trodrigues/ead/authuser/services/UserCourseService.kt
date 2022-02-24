package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import org.springframework.data.domain.Pageable
import java.util.*

interface UserCourseService {

    fun getAllCoursesByUser(userId: UUID, pageable: Pageable): PageResponse<CourseResponse>

}
