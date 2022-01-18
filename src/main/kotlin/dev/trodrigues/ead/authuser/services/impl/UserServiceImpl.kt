package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.extension.toModel
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
        checkIfExistsByUsername(userModel.username)
        checkIfExistsByEmail(userModel.email)
        return userRepository.save(userModel)
    }

    override fun update(userId: UUID, putUserRequest: PutUserRequest): UserModel {
        val oldUser = findById(userId)
        return userRepository.save(putUserRequest.toModel(oldUser))
    }

    override fun updatePassword(userId: UUID, patchPasswordRequest: PatchPasswordRequest) {
        val oldUser = findById(userId)
        if (oldUser.password != patchPasswordRequest.oldPassword) {
            throw ConflictException("Old password does not match")
        }
        userRepository.save(oldUser.copy(password = patchPasswordRequest.password))
    }

    private fun checkIfExistsByUsername(username: String) {
        if (userRepository.existsByUsername(username)) {
            throw ConflictException("Username already taken by another user")
        }
    }

    private fun checkIfExistsByEmail(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw ConflictException("Email already taken by another user")
        }
    }

}
