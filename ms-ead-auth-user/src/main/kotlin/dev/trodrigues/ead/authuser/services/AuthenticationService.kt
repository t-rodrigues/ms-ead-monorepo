package dev.trodrigues.ead.authuser.services

import dev.trodrigues.ead.authuser.controllers.requests.AuthRequest
import dev.trodrigues.ead.authuser.controllers.responses.JwtResponse

interface AuthenticationService {

    fun auth(authRequest: AuthRequest): JwtResponse

}
