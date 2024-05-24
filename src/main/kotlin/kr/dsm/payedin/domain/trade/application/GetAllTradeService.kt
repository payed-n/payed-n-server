package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeResponse
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetAllTradeService(
    private val tradeRepository: TradeRepository
) {

    fun execute(): GetAllTradeResponse {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id
        return GetAllTradeResponse(
            tradeRepository.findAll(userId)
        )
    }
}