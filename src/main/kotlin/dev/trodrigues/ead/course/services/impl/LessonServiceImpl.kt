package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.LessonModel
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.services.LessonService
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository
) : LessonService {

    @Transactional(readOnly = true)
    override fun getLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel {
        return lessonRepository.findLessonIntoModule(moduleId, lessonId).orElseThrow { NotFoundException("Lesson not found for this module: [$moduleId]") }
    }

    @Transactional
    override fun createLesson(lessonModel: LessonModel): LessonModel = lessonRepository.save(lessonModel)

    @Transactional
    override fun update(lessonModel: LessonModel): LessonModel = lessonRepository.save(lessonModel)

    @Transactional
    override fun deleteLesson(moduleId: UUID, lessonId: UUID) {
        val lesson = getLessonIntoModule(moduleId, lessonId)
        lessonRepository.delete(lesson)
    }

}
