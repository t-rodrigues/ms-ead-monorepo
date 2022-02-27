package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.UserCourseModel
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserCourseRepository : JpaRepository<UserCourseModel, UUID> {

    fun existsByUserAndCourseId(user: UserModel, courseId: UUID): Boolean

}
