package dev.trodrigues.ead.course.extension

import dev.trodrigues.ead.course.controllers.requests.*
import dev.trodrigues.ead.course.controllers.responses.*
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.LessonModel
import dev.trodrigues.ead.course.models.ModuleModel
import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.domain.Page
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

fun ModulePutRequest.toModel(previousValue: ModuleModel): ModuleModel {
    return previousValue.copy(
        title = this.title!!,
        description = this.description!!
    )
}

fun LessonPostRequest.toModel(moduleModel: ModuleModel): LessonModel = LessonModel(
    title = this.title!!,
    description = this.description,
    videoUrl = this.videoUrl!!,
    creationDate = LocalDateTime.now(ZoneId.of("UTC")),
    module = moduleModel
)

fun LessonModel.toResponse(): LessonResponse = LessonResponse(
    id = this.id!!,
    title = this.title,
    description = this.description,
    creationDate = this.creationDate
)

fun LessonPutRequest.toModel(previousValue: LessonModel): LessonModel = previousValue.copy(
    title = this.title!!,
    description = this.description,
    videoUrl = this.videoUrl!!
)

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse(
    items = this.content,
    currentPage = this.number,
    totalPages = this.totalPages,
    totalItems = this.totalElements
)

fun UserModel.toResponse(): UserResponse = UserResponse(
    id = this.id,
    fullName = this.fullName,
    email = this.email,
    userStatus = this.userStatus,
    userType = this.userType,
    cpf = this.cpf,
    imageUrl = this.imageUrl
)
