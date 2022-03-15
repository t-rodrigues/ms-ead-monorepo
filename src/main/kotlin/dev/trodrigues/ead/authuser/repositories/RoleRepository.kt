package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.enums.RoleType
import dev.trodrigues.ead.authuser.models.RoleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleModel, UUID> {

    fun getByName(role: RoleType): RoleModel

    fun findByName(role: RoleType): RoleModel?

}
