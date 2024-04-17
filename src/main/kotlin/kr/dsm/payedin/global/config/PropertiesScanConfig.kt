package kr.dsm.payedin.global.config

import kr.dsm.payedin.global.security.token.TokenProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(
    basePackages = ["kr.dsm.payedin.global"],
    basePackageClasses = [
        TokenProperties::class
    ]
)
@Configuration
class PropertiesScanConfig