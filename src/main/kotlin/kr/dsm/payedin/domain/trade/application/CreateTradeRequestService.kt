package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.trade.domain.value.TradeStatus
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateTradeRequestService(
    private val tradeRepository: TradeRepository,
    private val tradeRequestRepository: TradeRequestRepository,
) {
    fun execute(tradeId: UUID) {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id

        tradeRepository.findById(tradeId)?: throw NotFoundException("Trade not found")

        tradeRequestRepository.save(
            TradeRequest(
                tradeId,
                userId,
                status = TradeStatus.REQUESTED
            )
        )
    }
}