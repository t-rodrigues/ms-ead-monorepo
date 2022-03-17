package dev.trodrigues.ead.course.security.filters

import dev.trodrigues.ead.course.security.JwtProvider
import dev.trodrigues.ead.course.security.UserDetailsImpl
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getToken(request)
        token?.let {
            if (jwtProvider.isTokenValid(it)) {
                val userId = jwtProvider.getSubject(it)
                val roles = jwtProvider.getClaimName(it, "roles")
                val userDetails = UserDetailsImpl(UUID.fromString(userId), roles)
                val authentication =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (!authorization.isNullOrBlank() && authorization.startsWith("Bearer ")) {
            return authorization.replace("Bearer ", "")
        }
        return null
    }

}
