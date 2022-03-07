package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {

    @Modifying
    @Query("delete from tb_courses_users where user_id = :userId", nativeQuery = true)
    fun deleteCourseUserByUser(userId: UUID)

}
