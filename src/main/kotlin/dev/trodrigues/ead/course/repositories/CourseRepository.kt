package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.CourseModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CourseRepository : JpaRepository<CourseModel, UUID>