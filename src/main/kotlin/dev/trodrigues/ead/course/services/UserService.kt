package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface UserService {

    fun getUserById(userId: UUID): UserModel

    fun getCoursesByUser(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel>

    fun registerUser(userModel: UserModel): UserModel

    fun updateUser(userModel: UserModel): UserModel

    fun deleteUser(userId: UUID)

}
