package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.repositories.LessonRepository
import dev.trodrigues.ead.course.services.LessonService
import org.springframework.stereotype.Service

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository
) : LessonService {}