package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ModuleRepository : JpaRepository<ModuleModel, UUID>