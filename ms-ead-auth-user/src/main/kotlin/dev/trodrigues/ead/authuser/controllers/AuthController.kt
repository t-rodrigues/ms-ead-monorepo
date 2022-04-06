package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.requests.AuthRequest
import dev.trodrigues.ead.authuser.controllers.requests.PostUserRequest
import dev.trodrigues.ead.authuser.controllers.responses.JwtResponse
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.extension.toModel
import dev.trodrigues.ead.authuser.extension.toResponse
import dev.trodrigues.ead.authuser.services.AuthenticationService
import dev.trodrigues.ead.authuser.services.UserService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class AuthController(
    private val userService: UserService,
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: PostUserRequest): UserResponse {
        return userService.register(request.toModel()).toResponse()
    }

    @PostMapping
    fun login(@RequestBody @Valid request: AuthRequest, response: HttpServletResponse): JwtResponse {
        val jwtResponse = authenticationService.auth(request)
        response.addHeader("Authorization", "${jwtResponse.type} ${jwtResponse.accessToken}")
        return jwtResponse
    }

}
