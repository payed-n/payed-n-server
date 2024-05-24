package kr.dsm.payedin.domain.trade.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.dsm.payedin.domain.trade.domain.entity.QTrade.trade
import kr.dsm.payedin.domain.trade.domain.entity.QTradeRequest.tradeRequest
import kr.dsm.payedin.domain.trade.domain.entity.TradeRequest
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.trade.persistence.vo.QTradeRequestVO
import kr.dsm.payedin.domain.trade.persistence.vo.TradeRequestVO
import kr.dsm.payedin.domain.user.domain.entity.QUser.user
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TradeRequestRepositoryImpl(
    private val tradeRequestJpaRepository: TradeRequestJpaRepository,
    private val queryFactory: JPAQueryFactory
) : TradeRequestRepository {
    override fun findByUserIdAndTradeId(userId: UUID, tradeId: UUID): TradeRequest? =
        tradeRequestJpaRepository.findByTradeIdAndUserId(tradeId, userId)

    override fun save(tradeRequest: TradeRequest): TradeRequest =
        tradeRequestJpaRepository.save(tradeRequest)

    override fun findAllByUserId(userId: UUID): List<TradeRequestVO> =
        queryFactory
            .select(
                QTradeRequestVO(
                    trade.id,
                    trade.imageUrl,
                    trade.title,
                    trade.userId,
                    user.nickname
                )
            ).from(tradeRequest)
            .join(trade)
            .on(trade.id.eq(tradeRequest.id), trade.userId.eq(userId))
            .join(user).on(user.id.eq(trade.userId))
            .fetch()

    override fun delete(tradeRequest: TradeRequest) =
        tradeRequestJpaRepository.delete(tradeRequest)
}