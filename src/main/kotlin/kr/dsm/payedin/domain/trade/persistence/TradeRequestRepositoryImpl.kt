package kr.dsm.payedin.domain.trade.persistence

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TradeRequestRepositoryImpl(
    private val tradeRequestJpaRepository: TradeRequestJpaRepository
) : TradeRequestRepository {
    override fun findByUserIdAndTradeId(userId: UUID, tradeId: UUID): TradeRequest? =
        tradeRequestJpaRepository.findByTradeIdAndUserId(tradeId, userId)

    override fun save(tradeRequest: TradeRequest): TradeRequest =
        tradeRequestJpaRepository.save(tradeRequest)
}