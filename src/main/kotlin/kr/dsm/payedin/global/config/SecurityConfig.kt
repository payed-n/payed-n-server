package kr.dsm.payedin.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val filterConfig: FilterConfig
) {
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            formLogin { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }

            authorizeHttpRequests {
                authorize(HttpMethod.GET, "/users/sign-in", permitAll)
                authorize(HttpMethod.POST, "/users/sign-up", permitAll)
                authorize(HttpMethod.GET, "/users/point", authenticated)
                authorize(HttpMethod.GET, "/users", authenticated)

                authorize(HttpMethod.POST, "/wallets/transfer", authenticated)
                authorize(HttpMethod.GET, "/wallets/my", authenticated)
                authorize(HttpMethod.GET, "/wallets/my/history", authenticated)

                authorize(HttpMethod.GET, "/trades", authenticated)
                authorize(HttpMethod.GET, "/trades/{id}", authenticated)
                authorize(HttpMethod.POST, "/trades", authenticated)
                authorize(HttpMethod.DELETE, "/trades/{id}", authenticated)
                authorize(HttpMethod.POST, "/trades/trade/{id}", authenticated)
                authorize(anyRequest, denyAll)
            }
        }

        http.apply(filterConfig)
        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}