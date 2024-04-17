package kr.dsm.payedin.global.security.auth

import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.global.security.exception.InvalidTokenException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val id = UUID.fromString(username)
        val user = userRepository.findById(id) ?: throw InvalidTokenException

        return CustomUserDetail(user)
    }
}