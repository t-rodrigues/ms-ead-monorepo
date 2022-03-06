package dev.trodrigues.ead.course.services.impl

import dev.trodrigues.ead.course.models.UserModel
import dev.trodrigues.ead.course.repositories.UserRepository
import dev.trodrigues.ead.course.services.UserService
import dev.trodrigues.ead.course.services.exceptions.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getUserById(userId: UUID): UserModel {
        return userRepository.findById(userId).orElseThrow { NotFoundException("User not found: [$userId]") }
    }

    override fun getCoursesByUser(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel> =
        userRepository.findAll(spec, pageable)

    @Transactional
    override fun registerUser(userModel: UserModel): UserModel = userRepository.save(userModel)

    @Transactional
    override fun updateUser(userModel: UserModel): UserModel = userRepository.save(userModel)

    @Transactional
    override fun deleteUser(userId: UUID) = userRepository.deleteById(userId)

}
