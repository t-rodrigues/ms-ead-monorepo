package dev.trodrigues.ead.course.security

import dev.trodrigues.ead.course.security.exceptions.AuthException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtProvider {

    @Value("\${ead.auth.jwt.secret}")
    private val jwtSecret: String? = null

    fun isTokenValid(token: String): Boolean =
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (_: Exception) {
            false
        }

    fun getSubject(token: String): String = getClaims(token).subject

    fun getClaimName(token: String, claimName: String) = getClaims(token)[claimName].toString()

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtSecret!!).parseClaimsJws(token).body
        } catch (_: Exception) {
            throw AuthException("Invalid access token")
        }
    }

}
