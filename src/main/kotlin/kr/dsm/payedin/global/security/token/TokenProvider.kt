package kr.dsm.payedin.global.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID

@Component
class TokenProvider(
    private val tokenProperties: TokenProperties
) {

    fun generateToken(userId: UUID): String =
        Jwts.builder()
            .signWith(tokenProperties.secret, SignatureAlgorithm.HS256)
            .setId(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + (3600 * 1000)))
            .compact()
}