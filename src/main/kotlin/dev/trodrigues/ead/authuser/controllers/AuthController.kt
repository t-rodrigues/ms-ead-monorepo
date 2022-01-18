package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.requests.PostUserRequest
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.extension.toModel
import dev.trodrigues.ead.authuser.extension.toResponse
import dev.trodrigues.ead.authuser.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: PostUserRequest): UserResponse {
        return userService.register(request.toModel()).toResponse()
    }

}
