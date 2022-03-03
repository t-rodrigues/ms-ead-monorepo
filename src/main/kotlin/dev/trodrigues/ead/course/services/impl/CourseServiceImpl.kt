package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.clients.AuthUserClient
import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.enums.Errors
import dev.trodrigues.ead.course.extension.toCourseModel
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.repositories.CourseRepository
import dev.trodrigues.ead.course.repositories.CourseUserRepository
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.CourseService
import dev.trodrigues.ead.course.services.exceptions.ConflictException
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import feign.FeignException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository,
    private val courseUserRepository: CourseUserRepository,
    private val authUserClient: AuthUserClient
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
        val coursesUsers = courseUserRepository.findAllCourseUserIntoCourse(courseModel.id!!)
        if (coursesUsers.isNotEmpty()) {
            courseUserRepository.deleteAll(coursesUsers)
            try {
                authUserClient.deleteCourseInAuthUser(courseModel.id!!)
            } catch (ex: FeignException) {
                if(!ex.status().equals(HttpStatus.NO_CONTENT.value())){
                    throw ConflictException("${ex.message}")
                }
            }
        }
        courseRepository.delete(courseModel)
    }

}
