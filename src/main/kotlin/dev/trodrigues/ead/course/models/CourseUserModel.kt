package dev.trodrigues.ead.course.models

import java.util.*
import javax.persistence.*

@Entity(name = "tb_courses_users")
data class CourseUserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    @ManyToOne(fetch = FetchType.LAZY)
    val course: CourseModel,
    val userId: UUID
)
