package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.models.UserModel
import dev.trodrigues.ead.authuser.repositories.UserRepository
import dev.trodrigues.ead.authuser.services.UserService
import dev.trodrigues.ead.authuser.services.exceptions.ConflictException
import dev.trodrigues.ead.authuser.services.exceptions.ObjectNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun findAll(): List<UserModel> {
        return userRepository.findAll()
    }

    override fun findById(userId: UUID): UserModel {
        return userRepository.findById(userId).orElseThrow { ObjectNotFoundException("User not found: $userId") }
    }

    override fun delete(userId: UUID) {
        val user = findById(userId)
        userRepository.delete(user)
    }

    override fun register(userModel: UserModel): UserModel {
        if (userRepository.existsByUsername(userModel.username)) {
            throw ConflictException("Username already taken by another user")
        }
        if (userRepository.existsByEmail(userModel.email)) {
            throw ConflictException("Email already taken by another user")
        }
        return userRepository.save(userModel)
    }

}
