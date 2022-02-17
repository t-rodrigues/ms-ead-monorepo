package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.ModuleModel
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.ModuleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModuleServiceImpl(
    private val moduleRepository: ModuleRepository,
    private val lessonRepository: LessonRepository
) : ModuleService {

    @Transactional
    override fun delete(moduleModel: ModuleModel) {
        val lessons = lessonRepository.findAllLessonsIntoModule(moduleModel.id!!)
        if (lessons.isNotEmpty()) {
            lessonRepository.deleteAll(lessons)
        }
        moduleRepository.delete(moduleModel)
    }

}
