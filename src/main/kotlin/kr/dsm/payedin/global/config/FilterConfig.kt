package kr.dsm.payedin.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import kr.dsm.payedin.global.filter.ExceptionFilter
import kr.dsm.payedin.global.filter.TokenFilter
import kr.dsm.payedin.global.security.token.TokenParser
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class FilterConfig(
    private val tokenParser: TokenParser,
    private val objectMapper: ObjectMapper
) : SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(TokenFilter(tokenParser), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(objectMapper), TokenFilter::class.java)
    }

    override fun init(builder: HttpSecurity?) {}
}