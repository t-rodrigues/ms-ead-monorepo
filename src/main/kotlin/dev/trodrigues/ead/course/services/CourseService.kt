package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.CourseModel
import java.util.*

interface CourseService {

    fun delete(courseId: UUID)

    fun create(courseModel: CourseModel): CourseModel

}
