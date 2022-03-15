package dev.trodrigues.ead.authuser.models

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*

@Entity(name = "TB_USERS")
data class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val username: String,
    val email: String,
    val password: String,
    val fullName: String,
    @Enumerated(EnumType.STRING)
    val userStatus: UserStatus,
    @Enumerated(EnumType.STRING)
    val userType: UserType,
    val cpf: String,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    val creationDate: LocalDateTime? = LocalDateTime.now(ZoneId.of("UTC")),
    val lastUpdatedDate: LocalDateTime? = LocalDateTime.now(ZoneId.of("UTC")),
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "TB_USERS_ROLES",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<RoleModel> = mutableSetOf()
)
