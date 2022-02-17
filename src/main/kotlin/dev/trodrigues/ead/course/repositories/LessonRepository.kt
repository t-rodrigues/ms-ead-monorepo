package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.LessonModel
import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface LessonRepository : JpaRepository<LessonModel, UUID> {

    @Query("select * from tb_lessons where module_id = :moduleId", nativeQuery = true)
    fun findAllLessonsIntoModule(moduleId: UUID): List<LessonModel>

}
