package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.enums.Errors
import dev.trodrigues.ead.course.extension.toCourseModel
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.repositories.CourseRepository
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.CourseService
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository
) : CourseService {

    @Transactional
    override fun delete(courseId: UUID) {
        val courseModel =
            courseRepository.findById(courseId).orElseThrow { NotFoundException(Errors.NOT_FOUND.message) }
        val modules = moduleRepository.findAllModulesIntoCourse(courseModel.id!!)
        if (modules.isNotEmpty()) {
            modules.forEach {
                val lessons = lessonRepository.findAllLessonsIntoModule(it.id)
                if (lessons.isNotEmpty())
                    lessonRepository.deleteAll(lessons)
            }
            moduleRepository.deleteAll(modules)
        }
        courseRepository.delete(courseModel)
    }

    @Transactional
    override fun create(courseModel: CourseModel): CourseModel = courseRepository.save(courseModel)

    @Transactional
    override fun update(courseId: UUID, coursePutRequest: CoursePutRequest): CourseModel {
        val oldCourse = findById(courseId)
        val updatedCourse = coursePutRequest.toCourseModel(oldCourse)
        return courseRepository.save(updatedCourse)
    }

    override fun findById(courseId: UUID): CourseModel {
        return courseRepository.findById(courseId).orElseThrow { NotFoundException("Course not found: $courseId") }
    }

}
