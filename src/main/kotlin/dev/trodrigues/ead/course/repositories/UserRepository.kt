package dev.trodrigues.ead.course.repositories

import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel>
