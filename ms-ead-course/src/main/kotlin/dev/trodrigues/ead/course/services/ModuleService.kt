package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface ModuleService {

    fun getModulesByCourse(spec: Specification<ModuleModel>, pageable: Pageable): Page<ModuleModel>

    fun getModuleIntoCourse(courseId: UUID, moduleId: UUID): ModuleModel

    fun getModuleById(moduleId: UUID): ModuleModel

    fun create(moduleModel: ModuleModel): ModuleModel

    fun update(moduleModel: ModuleModel): ModuleModel

    fun delete(courseId: UUID, moduleId: UUID)

}
