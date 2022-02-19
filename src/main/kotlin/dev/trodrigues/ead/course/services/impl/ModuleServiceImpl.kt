package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.ModuleModel
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.ModuleService
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ModuleServiceImpl(
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository
) : ModuleService {

    @Transactional
    override fun create(moduleModel: ModuleModel): ModuleModel = moduleRepository.save(moduleModel)

    @Transactional
    override fun delete(courseId: UUID, moduleId: UUID) {
        val module = moduleRepository.findModuleIntoCourse(courseId, moduleId)
            .orElseThrow { NotFoundException("Module not found for this course: [$courseId]") }
        val lessons = lessonRepository.findAllLessonsIntoModule(module.id!!)
        if (lessons.isNotEmpty()) {
            lessonRepository.deleteAll(lessons)
        }
        moduleRepository.delete(module)
    }

}
