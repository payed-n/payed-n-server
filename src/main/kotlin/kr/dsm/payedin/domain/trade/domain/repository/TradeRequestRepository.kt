package kr.dsm.payedin.domain.trade.domain.repository

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import kr.dsm.payedin.domain.trade.persistence.vo.TradeRequestVO
import java.util.UUID

interface TradeRequestRepository {
    fun findByUserIdAndTradeId(userId: UUID, tradeId: UUID): TradeRequest?

    fun save(tradeRequest: TradeRequest): TradeRequest

    fun findAllByUserId(userId: UUID): List<TradeRequestVO>

    fun findById(tradeRequestId: UUID): TradeRequest?

    fun delete(tradeRequest: TradeRequest)
}