package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.repositories.RoleRepository
import dev.trodrigues.ead.authuser.services.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService
