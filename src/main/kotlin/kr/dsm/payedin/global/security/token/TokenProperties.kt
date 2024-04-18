package kr.dsm.payedin.global.security.token

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Base64
import javax.crypto.SecretKey

@ConfigurationProperties(prefix = "jwt")
class TokenProperties(
    secret: String
) {

    val secret: SecretKey = Keys.hmacShaKeyFor(
        Base64.getEncoder().encodeToString(secret.toByteArray())
            .toByteArray()
    )
}