package kr.dsm.payedin.domain.trade.domain.repository

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import java.util.UUID

interface TradeRequestRepository {
    fun findByUserIdAndTradeId(userId: UUID, tradeId: UUID): TradeRequest?

    fun save(tradeRequest: TradeRequest): TradeRequest
}