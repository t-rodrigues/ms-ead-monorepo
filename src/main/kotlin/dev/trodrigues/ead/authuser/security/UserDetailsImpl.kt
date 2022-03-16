package dev.trodrigues.ead.authuser.security

import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val userModel: UserModel) : UserDetails {

    val id = userModel.id!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        userModel.roles.map { SimpleGrantedAuthority(it.name!!.name) }.toMutableList()

    override fun getPassword(): String = userModel.password

    override fun getUsername(): String = userModel.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
