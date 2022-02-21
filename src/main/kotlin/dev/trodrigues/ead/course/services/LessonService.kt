package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.LessonModel
import java.util.*

interface LessonService {

    fun createLesson(lessonModel: LessonModel): LessonModel

    fun deleteLesson(moduleId: UUID, lessonId: UUID)

}
