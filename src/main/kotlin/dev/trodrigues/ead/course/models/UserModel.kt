package dev.trodrigues.ead.course.models

import dev.trodrigues.ead.course.enums.UserStatus
import dev.trodrigues.ead.course.enums.UserType
import java.util.*
import javax.persistence.*

@Entity(name = "tb_users")
data class UserModel(
    @Id
    val id: UUID,
    val fullName: String,
    val email: String,
    @Enumerated(EnumType.STRING)
    val userStatus: UserStatus,
    @Enumerated(EnumType.STRING)
    val userType: UserType,
    val cpf: String? = null,
    val imageUrl: String? = null,
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    val courses: Set<CourseModel> = mutableSetOf()
)
