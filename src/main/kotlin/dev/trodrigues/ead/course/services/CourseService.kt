package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.models.CourseModel
import java.util.*

interface CourseService {

    fun getCourses(): List<CourseModel>

    fun create(courseModel: CourseModel): CourseModel

    fun update(courseId: UUID, coursePutRequest: CoursePutRequest): CourseModel

    fun findById(courseId: UUID): CourseModel

    fun delete(courseId: UUID)

}
