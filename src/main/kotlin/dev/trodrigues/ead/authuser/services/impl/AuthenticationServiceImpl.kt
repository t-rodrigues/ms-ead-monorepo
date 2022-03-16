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
        val token = jwtProvider.generateJwt(userDetails.username)
        return JwtResponse(token)
    }

}
