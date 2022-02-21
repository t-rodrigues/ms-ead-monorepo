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
    private val moduleRepository: ModuleRepository, private val lessonRepository: LessonRepository
) : ModuleService {

    @Transactional(readOnly = true)
    override fun getModulesByCourse(courseId: UUID): List<ModuleModel> {
        return moduleRepository.findAllModulesIntoCourse(courseId)
    }

    @Transactional(readOnly = true)
    override fun getModuleIntoCourse(courseId: UUID, moduleId: UUID): ModuleModel {
        return moduleRepository.findModuleIntoCourse(courseId, moduleId)
            .orElseThrow { NotFoundException("Module not found for this course: [$courseId]") }
    }

    @Transactional(readOnly = true)
    override fun getModuleById(moduleId: UUID): ModuleModel {
        return moduleRepository.findById(moduleId).orElseThrow { NotFoundException("Module not found: [$moduleId]") }
    }

    @Transactional
    override fun create(moduleModel: ModuleModel): ModuleModel = moduleRepository.save(moduleModel)

    @Transactional
    override fun update(moduleModel: ModuleModel): ModuleModel = moduleRepository.save(moduleModel)

    @Transactional
    override fun delete(courseId: UUID, moduleId: UUID) {
        val module = getModuleIntoCourse(courseId, moduleId)
        val lessons = lessonRepository.findAllLessonsIntoModule(module.id!!)
        if (lessons.isNotEmpty()) {
            lessonRepository.deleteAll(lessons)
        }
        moduleRepository.delete(module)
    }

}
