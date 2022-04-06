package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.LessonModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface LessonService {

    fun getLessonsIntoModule(spec: Specification<LessonModel>, pageable: Pageable): Page<LessonModel>

    fun getLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel

    fun createLesson(lessonModel: LessonModel): LessonModel

    fun update(lessonModel: LessonModel): LessonModel

    fun deleteLesson(moduleId: UUID, lessonId: UUID)

}
