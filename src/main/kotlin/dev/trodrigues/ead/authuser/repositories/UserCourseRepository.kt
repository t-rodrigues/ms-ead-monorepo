package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.UserCourseModel
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserCourseRepository : JpaRepository<UserCourseModel, UUID> {

    fun existsByUserAndCourseId(user: UserModel, courseId: UUID): Boolean

    @Query("select * from tb_users_courses where user_id = :userId", nativeQuery = true)
    fun findAllUserCourseIntoUser(userId: UUID): List<UserCourseModel>

    fun existsByCourseId(courseId: UUID): Boolean

    fun deleteAllByCourseId(courseId: UUID)

}
