package dev.trodrigues.ead.course.models

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
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
    @Fetch(FetchMode.SUBSELECT)
    val lessons: Set<LessonModel> = mutableSetOf()

)
