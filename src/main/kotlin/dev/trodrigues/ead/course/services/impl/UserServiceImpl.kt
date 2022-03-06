package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.UserModel
import dev.trodrigues.ead.course.repositories.UserRepository
import dev.trodrigues.ead.course.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getCoursesByUser(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel> {
        return userRepository.findAll(spec, pageable)
    }

    override fun registerUser(userModel: UserModel): UserModel = userRepository.save(userModel)

}
