package kr.dsm.payedin.global.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import kr.dsm.payedin.global.security.auth.CustomUserDetailsService
import kr.dsm.payedin.global.security.exception.InvalidTokenException
import kr.dsm.payedin.global.security.exception.TokenExpiredException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class TokenParser(
    private val customUserDetailsService: CustomUserDetailsService,
    private val tokenProperties: TokenProperties
) {

    fun generateAuthenticationFromToken(token: String): Authentication {
        val claim = getClaim(token).also {
            if (it.body.id == null) {
                throw InvalidTokenException
            }
        }
        val userDetail = customUserDetailsService.loadUserByUsername(claim.body.id)

        return UsernamePasswordAuthenticationToken(userDetail, "", userDetail.authorities)
    }

    private fun getClaim(token: String): Jws<Claims> =
        try {
            Jwts.parserBuilder()
                .setSigningKey(tokenProperties.secret)
                .build()
                .parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            throw InvalidTokenException
        }
}