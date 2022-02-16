package dev.trodrigues.ead.course.models

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "tb_lessons")
data class LessonModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    val title: String,
    val description: String,
    val videoUrl: String? = null,
    val creationDate: LocalDateTime

)
