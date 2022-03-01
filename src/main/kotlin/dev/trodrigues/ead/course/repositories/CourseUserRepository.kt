package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.CourseUserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CourseUserRepository : JpaRepository<CourseUserModel, UUID> {

    fun existsByCourseAndUserId(courseModel: CourseModel, userId: UUID): Boolean

    @Query("select * from tb_courses_users where course_id = :courseId", nativeQuery = true)
    fun findAllCourseUserIntoCourse(courseId: UUID): List<CourseUserModel>

}
