package dev.trodrigues.ead.course.models

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "tb_lessons")
data class LessonModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val title: String,
    val description: String? = null,
    val videoUrl: String? = null,
    val creationDate: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    val module: ModuleModel

)
