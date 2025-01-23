package pl.boryskaczmarek.crypto_alert_system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain
import pl.boryskaczmarek.crypto_alert_system.service.CustomOAuth2UserService

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/", "/login").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { it ->
                it.userInfoEndpoint {
                    it.userService(CustomOAuth2UserService())
                }
            }
            .logout {
                it.logoutSuccessUrl("/")
            }

        return http.build()
    }
}