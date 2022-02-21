package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.LessonModel

interface LessonService {

    fun createLesson(lessonModel: LessonModel): LessonModel

}
