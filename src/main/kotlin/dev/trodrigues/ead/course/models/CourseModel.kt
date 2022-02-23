package dev.trodrigues.ead.course.models

import dev.trodrigues.ead.course.enums.CourseLevel
import dev.trodrigues.ead.course.enums.CourseStatus
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*

@Entity(name = "tb_courses")
data class CourseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: String,
    val description: String,
    @Enumerated(EnumType.STRING)
    val courseStatus: CourseStatus,
    @Enumerated(EnumType.STRING)
    val courseLevel: CourseLevel,
    val userInstructor: UUID,
    val imageUrl: String? = null,
    val creationDate: LocalDateTime? = LocalDateTime.now(ZoneId.of("UTC")),
    val lastUpdatedDate: LocalDateTime? = LocalDateTime.now(ZoneId.of("UTC")),
    @OneToMany(mappedBy = "course")
    @Fetch(FetchMode.SUBSELECT)
    val modules: Set<ModuleModel> = mutableSetOf(),
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    val coursesUsers: Set<CourseUserModel> = mutableSetOf()
)
