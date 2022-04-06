package dev.trodrigues.ead.course.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl(
    private val userId: UUID,
    private val rolesStr: String
) : UserDetails {

    val id = userId

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles = rolesStr.split(",").map { SimpleGrantedAuthority(it) }.toMutableList()
        return roles.toMutableList()
    }

    override fun getPassword(): String? = null

    override fun getUsername(): String = userId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
