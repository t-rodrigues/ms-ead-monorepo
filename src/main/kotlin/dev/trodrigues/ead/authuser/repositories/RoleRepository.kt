package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.RoleModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<RoleModel, UUID>
