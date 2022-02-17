package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.ModuleModel

interface ModuleService {

    fun delete(moduleModel: ModuleModel)

}
