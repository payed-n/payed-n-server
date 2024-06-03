package kr.dsm.payedin.domain.user.application

import kr.dsm.payedin.common.exception.ConflictException
import kr.dsm.payedin.domain.user.domain.entity.User
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.user.presentation.dto.SignUpRequest
import kr.dsm.payedin.domain.user.presentation.dto.TokenResponse
import kr.dsm.payedin.global.security.token.TokenProvider
import kr.dsm.payedin.thirdparty.api.FeignFacade
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Transactional
@Service
class SignUpService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val feignFacade: FeignFacade
) {

    fun execute(request: SignUpRequest): TokenResponse {
        if (userRepository.existsByGcnOrNickname(request.gcn, request.nickname)) {
            throw ConflictException("User already exists")
        }

        val dmsInfo = feignFacade.getDmsUserInfo(request.name, request.gcn)
        val user = userRepository.save(
            User(
                name = request.name,
                gcn = request.gcn,
                nickname = request.nickname,
                accountNumber = RandomStringUtils.randomNumeric(12),
                balance = 1000 + (dmsInfo.bonus_point * 1000) - (dmsInfo.minus_point * 500),
                bonusTotal = dmsInfo.bonus_point,
                minusTotal = dmsInfo.minus_point,
                dmsId = dmsInfo.id
            )
        )

        val token = tokenProvider.generateToken(user.id)
        return TokenResponse(token)
    }
}