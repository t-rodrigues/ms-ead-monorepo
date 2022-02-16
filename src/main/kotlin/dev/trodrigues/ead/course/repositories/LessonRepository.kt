package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.LessonModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LessonRepository : JpaRepository<LessonModel, UUID>