package dev.trodrigues.ead.course.security

import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationEntryPointImpl : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        println(authException.message)
        response.contentType = MediaType.APPLICATION_JSON.toString()
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }

}
