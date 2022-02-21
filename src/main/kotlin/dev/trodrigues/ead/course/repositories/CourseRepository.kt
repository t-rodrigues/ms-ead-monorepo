package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.CourseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface CourseRepository : JpaRepository<CourseModel, UUID>, JpaSpecificationExecutor<CourseModel>
