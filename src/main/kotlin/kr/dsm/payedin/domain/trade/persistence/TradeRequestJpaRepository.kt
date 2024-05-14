package kr.dsm.payedin.domain.trade.persistence

import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TradeRequestJpaRepository : CrudRepository<TradeRequest, UUID>{
    fun findByTradeIdAndUserId(tradeId: UUID, userId: UUID): TradeRequest?
}