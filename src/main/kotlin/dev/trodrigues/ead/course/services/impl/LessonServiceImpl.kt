package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.LessonModel
import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.services.LessonService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository
) : LessonService {

    @Transactional
    override fun createLesson(lessonModel: LessonModel): LessonModel = lessonRepository.save(lessonModel)

}
