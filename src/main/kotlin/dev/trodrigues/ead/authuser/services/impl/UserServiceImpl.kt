package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.clients.CourseClient
import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PatchUserAvatarRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.extension.toModel
import dev.trodrigues.ead.authuser.models.UserModel
import dev.trodrigues.ead.authuser.repositories.UserCourseRepository
import dev.trodrigues.ead.authuser.repositories.UserRepository
import dev.trodrigues.ead.authuser.services.UserService
import dev.trodrigues.ead.authuser.services.exceptions.ConflictException
import dev.trodrigues.ead.authuser.services.exceptions.ObjectNotFoundException
import feign.FeignException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userCourseRepository: UserCourseRepository,
    private val courseClient: CourseClient
) : UserService {

    override fun findAll(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel> {
        return userRepository.findAll(spec, pageable)
    }

    override fun findById(userId: UUID): UserModel {
        return userRepository.findById(userId).orElseThrow { ObjectNotFoundException("User not found: $userId") }
    }

    override fun delete(userId: UUID) {
        val user = findById(userId)
        val usersCourses = userCourseRepository.findAllUserCourseIntoUser(user.id!!)
        if (usersCourses.isNotEmpty()) {
            userCourseRepository.deleteAll(usersCourses)
            try {
                courseClient.deleteCourseUserByUser(user.id)
            } catch (ex: FeignException) {
                throw ConflictException("${ex.message}")
            }
        }
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
        userRepository.save(patchPasswordRequest.toModel(oldUser))
    }

    override fun updateAvatar(userId: UUID, patchUserAvatarRequest: PatchUserAvatarRequest) {
        val oldUser = findById(userId)
        userRepository.save(patchUserAvatarRequest.toModel(oldUser))
    }

    override fun updateUserType(userId: UUID, userType: UserType): UserModel {
        val oldUser = findById(userId)
        val updatedUser = oldUser.copy(
            userType = userType,
            lastUpdatedDate = LocalDateTime.now(ZoneId.of("UTC"))
        )
        return userRepository.save(updatedUser)
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
