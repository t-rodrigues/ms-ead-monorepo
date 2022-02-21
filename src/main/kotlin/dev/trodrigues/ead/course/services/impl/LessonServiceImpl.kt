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

    @Transactional
    override fun createLesson(lessonModel: LessonModel): LessonModel = lessonRepository.save(lessonModel)

    @Transactional
    override fun deleteLesson(moduleId: UUID, lessonId: UUID) {
        val lesson = lessonRepository.findLessonIntoModule(moduleId, lessonId).orElseThrow { NotFoundException("Lesson not found for this module: [$moduleId]") }
        lessonRepository.delete(lesson)
    }

}
