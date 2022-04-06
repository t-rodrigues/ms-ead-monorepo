package dev.trodrigues.ead.authuser.security

import dev.trodrigues.ead.authuser.security.exceptions.AuthException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    @Value("\${ead.auth.jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${ead.auth.jwt.expiresIn}")
    private val jwtExpiresIn: Int? = null

    fun generateJwt(username: String, claims: String? = null): String =
        Jwts.builder().setSubject(username)
            .claim("roles", claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiresIn!!))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()

    fun isTokenValid(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun getSubject(token: String): String = getClaims(token).subject

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtSecret!!).parseClaimsJws(token).body
        } catch (_: Exception) {
            throw AuthException("Invalid access token")
        }
    }

}
