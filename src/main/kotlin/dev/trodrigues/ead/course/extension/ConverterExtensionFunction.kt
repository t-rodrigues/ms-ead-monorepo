package dev.trodrigues.ead.course.extension

import dev.trodrigues.ead.course.controllers.requests.CoursePostRequest
import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.controllers.requests.ModulePostRequest
import dev.trodrigues.ead.course.controllers.responses.CourseResponse
import dev.trodrigues.ead.course.controllers.responses.ModuleResponse
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.ModuleModel
import java.time.LocalDateTime
import java.time.ZoneId

fun CoursePostRequest.toCourseModel(): CourseModel = CourseModel(
    name = this.name!!,
    description = this.description!!,
    courseLevel = this.courseLevel!!,
    courseStatus = this.courseStatus!!,
    userInstructor = this.userInstructor!!,
    creationDate = LocalDateTime.now(ZoneId.of("UTC")),
    lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
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

fun CoursePutRequest.toCourseModel(courseModel: CourseModel): CourseModel {
    return courseModel.copy(
        name = this.name!!,
        description = this.description!!,
        courseStatus = this.courseStatus!!,
        courseLevel = this.courseLevel!!,
        lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
    )
}

fun ModulePostRequest.toModel(courseModel: CourseModel): ModuleModel = ModuleModel(
    title = this.title!!,
    description = this.description!!,
    creationDate = LocalDateTime.now(ZoneId.of("UTC")),
    course = courseModel
)

fun ModuleModel.toResponse(): ModuleResponse = ModuleResponse(
    id = this.id!!,
    title = this.title,
    description = this.description,
    creationDate = this.creationDate!!
)
