package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.LessonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LessonRepository : JpaRepository<LessonModel, UUID> {

    @Query("select * from tb_lessons where module_id = :moduleId", nativeQuery = true)
    fun findAllLessonsIntoModule(moduleId: UUID): List<LessonModel>

    @Query("select * from tb_lessons where id = :lessonId and module_id = :moduleId", nativeQuery = true)
    fun findLessonIntoModule(moduleId: UUID, lessonId: UUID): Optional<LessonModel>

}
