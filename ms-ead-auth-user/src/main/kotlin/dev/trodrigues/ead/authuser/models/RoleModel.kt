package dev.trodrigues.ead.authuser.models

import dev.trodrigues.ead.authuser.enums.RoleType
import java.util.*
import javax.persistence.*

@Entity(name = "tb_roles")
data class RoleModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @Enumerated(EnumType.STRING)
    val name: RoleType? = null
)
