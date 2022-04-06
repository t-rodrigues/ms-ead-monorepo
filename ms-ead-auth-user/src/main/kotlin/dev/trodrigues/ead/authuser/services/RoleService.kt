package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.enums.RoleType
import dev.trodrigues.ead.authuser.models.RoleModel

interface RoleService {

    fun findByName(roleType: RoleType): RoleModel

}
