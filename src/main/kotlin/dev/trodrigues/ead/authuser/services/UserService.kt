package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.models.UserModel
import java.util.*

interface UserService {

    fun findAll(): List<UserModel>

    fun findById(userId: UUID): UserModel

    fun delete(userId: UUID)

    fun register(userModel: UserModel): UserModel

    fun update(userId: UUID, putUserRequest: PutUserRequest): UserModel

}
