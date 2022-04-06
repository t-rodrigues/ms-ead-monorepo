package dev.trodrigues.ead.authuser.security

import dev.trodrigues.ead.authuser.repositories.UserRepository
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Username not found: $username")
        return UserDetailsImpl(user)
    }

    fun loadUserById(userId: UUID): UserDetails {
        val user = userRepository.findById(userId)
            .orElseThrow { AuthenticationCredentialsNotFoundException("User not found id: $userId") }
        return UserDetailsImpl(user)
    }

}
