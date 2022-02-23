package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.CourseUserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourseUserRepository : JpaRepository<CourseUserModel, UUID>
