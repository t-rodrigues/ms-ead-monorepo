package dev.trodrigues.ead.authuser.security

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

    fun generateJwt(username: String): String =
        Jwts.builder().setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiresIn!!))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()

    fun getSubject(token: String): String =
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject

    fun isTokenValid(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (_: Exception) {
            false
        }
    }

}
