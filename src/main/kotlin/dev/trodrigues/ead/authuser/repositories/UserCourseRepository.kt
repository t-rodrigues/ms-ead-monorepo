package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.UserCourseModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserCourseRepository: JpaRepository<UserCourseModel, UUID>
