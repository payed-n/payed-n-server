package kr.dsm.payedin.global.config

import kr.dsm.payedin.thirdparty.api.error.FeignExceptionConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun errorDecoder() = FeignExceptionConfig()
}