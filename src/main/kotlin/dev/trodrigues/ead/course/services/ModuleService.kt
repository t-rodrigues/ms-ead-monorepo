package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.ModuleModel

interface ModuleService {

    fun create(moduleModel: ModuleModel): ModuleModel

    fun delete(moduleModel: ModuleModel)

}
