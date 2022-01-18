package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.requests.PutUserRequest
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.extension.toResponse
import dev.trodrigues.ead.authuser.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(): List<UserResponse> {
        return userService.findAll().map { it.toResponse() }
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: UUID): UserResponse {
        return userService.findById(userId).toResponse()
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: UUID, @RequestBody request: PutUserRequest): UserResponse {
        return userService.update(userId, request).toResponse()
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserById(@PathVariable userId: UUID) {
        userService.delete(userId)
    }

}
