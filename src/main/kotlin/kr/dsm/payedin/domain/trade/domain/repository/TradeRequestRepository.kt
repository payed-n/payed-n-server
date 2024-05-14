package kr.dsm.payedin.domain.trade.domain.repository

import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import java.util.UUID

interface TradeRequestRepository {
    fun findByUserIdAndTradeId(userId: UUID, tradeId: UUID): TradeRequest?
}