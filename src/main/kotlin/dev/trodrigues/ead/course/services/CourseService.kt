package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.CourseModel

interface CourseService {

    fun delete(courseModel: CourseModel)

}
