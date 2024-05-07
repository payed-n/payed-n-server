package kr.dsm.payedin.domain.user.application

import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.user.exception.UserNotFoundException
import kr.dsm.payedin.domain.user.presentation.dto.TokenResponse
import kr.dsm.payedin.global.security.token.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class LoginService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider
) {

    fun execute(gcn: String): TokenResponse {
        val user = userRepository.findByGcn(gcn)
            ?: throw UserNotFoundException

        return TokenResponse(
            tokenProvider.generateToken(user.id)
        )
    }
}