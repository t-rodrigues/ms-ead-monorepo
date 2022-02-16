package dev.trodrigues.ead.course.models

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity(name = "tb_modules")
data class ModuleModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    val title: String,
    val description: String,
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    val course: CourseModel,
    @OneToMany(mappedBy = "module")
    val lessons: Set<LessonModel> = mutableSetOf()

)
