package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.ModuleModel
import java.util.*

interface ModuleService {

    fun getModuleIntoCourse(courseId: UUID, moduleId: UUID): ModuleModel

    fun create(moduleModel: ModuleModel): ModuleModel

    fun update(moduleModel: ModuleModel): ModuleModel

    fun delete(courseId: UUID, moduleId: UUID)

}
