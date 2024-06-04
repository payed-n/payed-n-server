package kr.dsm.payedin.domain.user.application

import kr.dsm.payedin.domain.user.domain.entity.User
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.user.presentation.dto.LoginRequest
import kr.dsm.payedin.domain.user.presentation.dto.TokenResponse
import kr.dsm.payedin.global.security.token.TokenProvider
import kr.dsm.payedin.thirdparty.api.FeignFacade
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LoginService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val feignFacade: FeignFacade
) {

    fun execute(request: LoginRequest): TokenResponse {
        var user = userRepository.findByNickname(request.accountId)
        val dmsInfo = feignFacade.login(
            request.accountId,
            request.password
        )

        if (user == null) {
            user = userRepository.save(
                User(
                    name = dmsInfo.name,
                    gcn = dmsInfo.gcn,
                    nickname = request.accountId,
                    accountNumber = RandomStringUtils.randomNumeric(12),
                    balance = 1000 + (dmsInfo.bonus_point * 1000) - (dmsInfo.minus_point * 500),
                    bonusTotal = dmsInfo.bonus_point,
                    minusTotal = dmsInfo.minus_point
                )
            )
        }

        return TokenResponse(
            tokenProvider.generateToken(user.id)
        )
    }
}