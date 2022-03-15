package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.enums.RoleType
import dev.trodrigues.ead.authuser.models.RoleModel
import dev.trodrigues.ead.authuser.repositories.RoleRepository
import dev.trodrigues.ead.authuser.services.RoleService
import dev.trodrigues.ead.authuser.services.exceptions.ObjectNotFoundException
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {

    override fun findByName(roleType: RoleType): RoleModel =
        roleRepository.findByName(roleType) ?: throw ObjectNotFoundException("Role not found: [$roleType]")

}
