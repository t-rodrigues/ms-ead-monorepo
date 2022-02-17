package dev.trodrigues.ead.course.extension

import dev.trodrigues.ead.course.controllers.requests.CoursePostRequest
import dev.trodrigues.ead.course.controllers.responses.CourseResponse
import dev.trodrigues.ead.course.models.CourseModel
import java.time.LocalDateTime

fun CoursePostRequest.toCourseModel(): CourseModel = CourseModel(
    name = this.name!!,
    description = this.description!!,
    courseLevel = this.courseLevel!!,
    courseStatus = this.courseStatus!!,
    userInstructor = this.userInstructor!!,
    creationDate = LocalDateTime.now(),
    lastUpdatedDate = LocalDateTime.now()
)

fun CourseModel.toResponse(): CourseResponse = CourseResponse(
    id = this.id!!,
    name = this.name,
    description = this.description,
    courseStatus = this.courseStatus,
    courseLevel = this.courseLevel,
    userInstructor = this.userInstructor,
    creationDate = this.creationDate,
    lastUpdateDate = this.lastUpdatedDate
)
