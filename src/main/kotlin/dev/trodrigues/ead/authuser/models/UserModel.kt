package dev.trodrigues.ead.authuser.models

import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "TB_USERS")
data class UserModel(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    val lastUpdatedDate: LocalDateTime? = null

)
