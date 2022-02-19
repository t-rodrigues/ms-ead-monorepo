package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ModuleRepository : JpaRepository<ModuleModel, UUID> {

    @Query("select * from tb_modules where course_id = :courseId", nativeQuery = true)
    fun findAllModulesIntoCourse(courseId: UUID): List<ModuleModel>

    @Query("select * from tb_modules where id = :moduleId and course_id = :courseId", nativeQuery = true)
    fun findModuleIntoCourse(courseId: UUID, moduleId: UUID): Optional<ModuleModel>

}
