package dev.trodrigues.ead.authuser.security.filters

import dev.trodrigues.ead.authuser.security.JwtProvider
import dev.trodrigues.ead.authuser.security.UserDetailsServiceImpl
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsServiceImpl
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
                val userDetails = userDetailsService.loadUserById(UUID.fromString(userId))
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
