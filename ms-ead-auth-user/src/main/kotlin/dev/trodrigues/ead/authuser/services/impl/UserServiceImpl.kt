package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PatchUserAvatarRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.enums.ActionType
import dev.trodrigues.ead.authuser.enums.RoleType
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.extension.toModel
import dev.trodrigues.ead.authuser.extension.toUserEvent
import dev.trodrigues.ead.authuser.models.UserModel
import dev.trodrigues.ead.authuser.publishers.UserEventPublisher
import dev.trodrigues.ead.authuser.repositories.RoleRepository
import dev.trodrigues.ead.authuser.repositories.UserRepository
import dev.trodrigues.ead.authuser.services.UserService
import dev.trodrigues.ead.authuser.services.exceptions.ConflictException
import dev.trodrigues.ead.authuser.services.exceptions.ObjectNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userEventPublisher: UserEventPublisher
) : UserService {

    @Transactional(readOnly = true)
    override fun findAll(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel> {
        return userRepository.findAll(spec, pageable)
    }

    @Transactional(readOnly = true)
    override fun findById(userId: UUID): UserModel {
        return userRepository.findById(userId).orElseThrow { ObjectNotFoundException("User not found: $userId") }
    }

    @Transactional
    override fun delete(userId: UUID) {
        val user = findById(userId)
        userRepository.delete(user)
        userEventPublisher.publishUserEvent(user.toUserEvent(ActionType.DELETE))
    }

    @Transactional
    override fun register(userModel: UserModel): UserModel {
        checkIfExistsByUsername(userModel.username)
        checkIfExistsByEmail(userModel.email)
        val role = roleRepository.getByName(RoleType.ROLE_STUDENT)
        val user = userModel.copy(
            roles = setOf(role),
            password = passwordEncoder.encode(userModel.password)
        )
        userRepository.save(user)
        userEventPublisher.publishUserEvent(user.toUserEvent(ActionType.CREATE))
        return user
    }

    @Transactional
    override fun update(userId: UUID, putUserRequest: PutUserRequest): UserModel {
        val oldUser = findById(userId)
        val updatedUser = userRepository.save(putUserRequest.toModel(oldUser))
        userEventPublisher.publishUserEvent(updatedUser.toUserEvent(ActionType.UPDATE))
        return updatedUser
    }

    @Transactional
    override fun updatePassword(userId: UUID, patchPasswordRequest: PatchPasswordRequest) {
        val oldUser = findById(userId)
        if (oldUser.password != patchPasswordRequest.oldPassword) {
            throw ConflictException("Old password does not match")
        }
        userRepository.save(patchPasswordRequest.toModel(oldUser))
    }

    @Transactional
    override fun updateAvatar(userId: UUID, patchUserAvatarRequest: PatchUserAvatarRequest) {
        val oldUser = findById(userId)
        val updatedUser = userRepository.save(patchUserAvatarRequest.toModel(oldUser))
        userEventPublisher.publishUserEvent(updatedUser.toUserEvent(ActionType.UPDATE))
    }

    @Transactional
    override fun updateUserType(userId: UUID, userType: UserType): UserModel {
        val oldUser = findById(userId)
        val role = RoleType.values().find { it.name.contains(userType.toString()) } ?: RoleType.ROLE_STUDENT
        val roles = oldUser.roles + setOf(roleRepository.getByName(role))
        val updatedUser = oldUser.copy(
            userType = userType,
            roles = roles,
            lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
        )
        userRepository.save(updatedUser)
        userEventPublisher.publishUserEvent(updatedUser.toUserEvent(ActionType.UPDATE))
        return updatedUser
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
