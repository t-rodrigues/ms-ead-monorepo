package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.enums.Errors
import dev.trodrigues.ead.course.enums.UserStatus
import dev.trodrigues.ead.course.extension.toCourseModel
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.UserModel
import dev.trodrigues.ead.course.publishers.NotificationCommandPublisher
import dev.trodrigues.ead.course.publishers.responses.NotificationCommand
import dev.trodrigues.ead.course.repositories.CourseRepository
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.repositories.UserRepository
import dev.trodrigues.ead.course.services.CourseService
import dev.trodrigues.ead.course.services.exceptions.ConflictException
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository,
    private val userRepository: UserRepository,
    private val notificationCommandPublisher: NotificationCommandPublisher
) : CourseService {

    @Transactional(readOnly = true)
    override fun getCourses(spec: Specification<CourseModel>, pageable: Pageable): Page<CourseModel> {
        return courseRepository.findAll(spec, pageable)
    }

    @Transactional(readOnly = true)
    override fun getCourseById(courseId: UUID): CourseModel {
        return courseRepository.findById(courseId)
            .orElseThrow { NotFoundException(Errors.NOT_FOUND.message.format("Course", courseId)) }
    }

    @Transactional
    override fun create(courseModel: CourseModel): CourseModel = courseRepository.save(courseModel)

    @Transactional
    override fun update(courseId: UUID, coursePutRequest: CoursePutRequest): CourseModel {
        val oldCourse = getCourseById(courseId)
        val updatedCourse = coursePutRequest.toCourseModel(oldCourse)
        return courseRepository.save(updatedCourse)
    }

    @Transactional
    override fun delete(courseId: UUID) {
        val courseModel =
            courseRepository.findById(courseId).orElseThrow { NotFoundException(Errors.NOT_FOUND.message) }
        val modules = moduleRepository.findAllModulesIntoCourse(courseModel.id!!)
        if (modules.isNotEmpty()) {
            modules.forEach { module ->
                val lessons = lessonRepository.findAllLessonsIntoModule(module.id!!)
                if (lessons.isNotEmpty())
                    lessonRepository.deleteAll(lessons)
            }
            moduleRepository.deleteAll(modules)
        }
        courseRepository.deleteCourseUserByCourse(courseModel.id)
        courseRepository.delete(courseModel)
    }

    @Transactional
    override fun saveSubscriptionUserInCourse(courseId: UUID, userId: UUID) {
        val course = getCourseById(courseId)
        val user = userRepository.findById(userId).orElseThrow { NotFoundException("User not found: [$userId]") }
        if (courseRepository.existsByCourseAndUser(course.id!!, user.id)) {
            throw ConflictException("User already enrolled.")
        }
        if (user.userStatus.equals(UserStatus.BLOCKED)) {
            throw ConflictException("User is blocked. [${user.id}]")
        }
        courseRepository.saveCourseUser(course.id, user.id)
        sendNotification(course, user)
    }

    private fun sendNotification(courseModel: CourseModel, userModel: UserModel) {
        try {
            val title = "Bem-vindo(a) ao Curso: ${courseModel.name}"
            val message = "${userModel.fullName} a sua inscri????o foi realizada com sucesso!"
            val notificationCommand = NotificationCommand(title, message, userModel.id)
            notificationCommandPublisher.publishNotificationCommand(notificationCommand)
        } catch (_: Exception) {

        }
    }

}
