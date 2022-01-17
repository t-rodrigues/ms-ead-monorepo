package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserModel, UUID> {}