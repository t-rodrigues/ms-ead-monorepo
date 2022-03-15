package dev.trodrigues.ead.authuser.security

import dev.trodrigues.ead.authuser.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Username not found: $username")
        return UserDetailsImpl(user)
    }
}
