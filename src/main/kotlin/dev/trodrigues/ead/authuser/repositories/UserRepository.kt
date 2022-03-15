package dev.trodrigues.ead.authuser.repositories

import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    @EntityGraph(attributePaths = ["roles"], type = EntityGraph.EntityGraphType.FETCH)
    fun findByUsername(username: String): UserModel?

}
