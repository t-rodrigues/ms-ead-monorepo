package dev.trodrigues.ead.authuser.services.impl

import dev.trodrigues.ead.authuser.controllers.requests.AuthRequest
import dev.trodrigues.ead.authuser.controllers.responses.JwtResponse
import dev.trodrigues.ead.authuser.security.JwtProvider
import dev.trodrigues.ead.authuser.security.UserDetailsImpl
import dev.trodrigues.ead.authuser.services.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: JwtProvider
) : AuthenticationService {

    override fun auth(authRequest: AuthRequest): JwtResponse {
        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        val authentication = authenticationManager.authenticate(authToken)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.joinTo(StringBuilder(""), ",") { role -> role.authority }
        println(roles)
        val token = jwtProvider.generateJwt(userDetails.id.toString(), roles.toString())
        return JwtResponse(token)
    }

}
