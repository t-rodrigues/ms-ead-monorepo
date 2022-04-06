package dev.trodrigues.configserver.configs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${ead.config-server.username}")
    private val username: String? = null

    @Value("\${ead.config-server.password}")
    private val password: String? = null

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser(username)
            .password(passwordEncoder().encode(password))
            .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .and().authorizeRequests()
            .anyRequest()
            .authenticated()
            .and().csrf().disable()
            .formLogin()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

}
