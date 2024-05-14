package kr.dsm.payedin.domain.user.application

import kr.dsm.payedin.domain.user.domain.entity.User
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.user.exception.UserNotFoundException
import kr.dsm.payedin.domain.user.presentation.dto.GetUsernameResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetUsernameService(
    private val userRepository: UserRepository
) {

    fun execute(accountNumber: String): GetUsernameResponse {
        val user = userRepository.findByAccountNumber(accountNumber)
            ?: throw UserNotFoundException

        return GetUsernameResponse(user.name)
    }
}