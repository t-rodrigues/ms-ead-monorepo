package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.repositories.ModuleRepository
import dev.trodrigues.ead.course.services.ModuleService
import org.springframework.stereotype.Service

@Service
class ModuleServiceImpl(
    private val moduleRepository: ModuleRepository
) : ModuleService {}