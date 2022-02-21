package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.LessonModel
import java.util.*

interface LessonService {

    fun getLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel

    fun createLesson(lessonModel: LessonModel): LessonModel

    fun update(lessonModel: LessonModel): LessonModel

    fun deleteLesson(moduleId: UUID, lessonId: UUID)

}
