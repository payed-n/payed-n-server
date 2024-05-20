package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeRequestResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetAllTradeRequestService(
    private val tradeRequestRepository: TradeRequestRepository,
) {

    fun execute(tradeId: UUID): GetAllTradeRequestResponse =
        GetAllTradeRequestResponse(
            tradeRequestRepository.findAllByTradeId(tradeId)
        )
}