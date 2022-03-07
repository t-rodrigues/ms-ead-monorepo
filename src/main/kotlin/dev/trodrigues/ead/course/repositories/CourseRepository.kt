package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.CourseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository : JpaRepository<CourseModel, UUID>, JpaSpecificationExecutor<CourseModel> {

    @Query(
        value = "select case when count(*) > 0 then true else false end from tb_courses_users where course_id = :courseId and user_id = :userId",
        nativeQuery = true
    )
    fun existsByCourseAndUser(courseId: UUID, userId: UUID): Boolean

    @Modifying
    @Query("insert into tb_courses_users (course_id, user_id) values (:courseId, :userId)", nativeQuery = true)
    fun saveCourseUser(courseId: UUID, userId: UUID)

}
