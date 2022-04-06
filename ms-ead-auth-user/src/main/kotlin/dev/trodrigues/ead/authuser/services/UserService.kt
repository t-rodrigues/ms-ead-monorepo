package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.controllers.requests.PatchPasswordRequest
import dev.trodrigues.ead.authuser.controllers.requests.PatchUserAvatarRequest
import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface UserService {

    fun findAll(spec: Specification<UserModel>, pageable: Pageable): Page<UserModel>

    fun findById(userId: UUID): UserModel

    fun delete(userId: UUID)

    fun register(userModel: UserModel): UserModel

    fun update(userId: UUID, putUserRequest: PutUserRequest): UserModel

    fun updatePassword(userId: UUID, patchPasswordRequest: PatchPasswordRequest)

    fun updateAvatar(userId: UUID, patchUserAvatarRequest: PatchUserAvatarRequest)

    fun updateUserType(userId: UUID, userType: UserType): UserModel

}
