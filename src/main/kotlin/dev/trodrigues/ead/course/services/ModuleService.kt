package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.ModuleModel
import java.util.*

interface ModuleService {

    fun create(moduleModel: ModuleModel): ModuleModel

    fun delete(courseId: UUID, moduleId: UUID)

}
