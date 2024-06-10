package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.ForbiddenException
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class CancelTradeRequestService(
    private val tradeRequestRepository: TradeRequestRepository
) {

    fun execute(tradeRequestId: UUID) {
        val currentUser = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user
        val tradeRequest = tradeRequestRepository.findById(tradeRequestId)!!

        if (tradeRequest.userId != currentUser.id) {
            throw ForbiddenException("Invalid User")
        }

        tradeRequestRepository.delete(tradeRequest)
    }
}