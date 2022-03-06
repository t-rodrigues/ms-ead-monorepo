package dev.trodrigues.ead.course.services

import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface UserService {

    fun getCoursesByUser(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel>

    fun registerUser(userModel: UserModel): UserModel

}
