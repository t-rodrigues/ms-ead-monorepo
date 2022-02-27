package dev.trodrigues.ead.authuser.models

import java.util.*
import javax.persistence.*

@Entity(name = "TB_USERS_COURSES")
data class UserCourseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val user: UserModel,
    val courseId: UUID
)
