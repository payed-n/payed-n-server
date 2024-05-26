package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.trade.presentation.dto.GetTradeInfoResponse
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class GetTradeInfoService(
    private val tradeRepository: TradeRepository,
    private val userRepository: UserRepository,
    private val tradeRequestRepository: TradeRequestRepository
) {

    fun execute(tradeId: UUID): GetTradeInfoResponse {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id
        val trade = tradeRepository.findById(tradeId)
            ?: throw NotFoundException("Trade Not Found")
        val user = userRepository.findById(trade.userId)!!

        return GetTradeInfoResponse(
            trade = trade,
            user = user,
            status = tradeRequestRepository.findByUserIdAndTradeId(userId, tradeId)?.status
        )
    }
}