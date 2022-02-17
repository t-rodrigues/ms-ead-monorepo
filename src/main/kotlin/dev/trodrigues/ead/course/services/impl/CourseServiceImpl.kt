package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.repositories.CourseRepository
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.CourseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository
) : CourseService {

    @Transactional
    override fun delete(courseModel: CourseModel) {
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

    override fun create(courseModel: CourseModel): CourseModel = courseRepository.save(courseModel)

}
