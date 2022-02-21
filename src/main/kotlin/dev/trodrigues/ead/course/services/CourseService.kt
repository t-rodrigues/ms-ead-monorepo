package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.models.CourseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface CourseService {

    fun getCourses(spec: Specification<CourseModel>, pageable: Pageable): Page<CourseModel>

    fun getCourseById(courseId: UUID): CourseModel

    fun create(courseModel: CourseModel): CourseModel

    fun update(courseId: UUID, coursePutRequest: CoursePutRequest): CourseModel

    fun delete(courseId: UUID)

}
