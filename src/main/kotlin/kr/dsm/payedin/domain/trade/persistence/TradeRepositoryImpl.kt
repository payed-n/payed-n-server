package kr.dsm.payedin.domain.trade.persistence

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.persistence.vo.QTradeVO
import kr.dsm.payedin.domain.trade.domain.entity.QTrade.trade
import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.user.domain.entity.QUser.user
import kr.dsm.payedin.domain.trade.persistence.vo.TradeVO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TradeRepositoryImpl(
    private val tradeJpaRepository: TradeJpaRepository,
    private val queryFactory: JPAQueryFactory
) : TradeRepository {
    override fun findAll(userId: UUID): List<TradeVO> =
        queryFactory
            .select(
                QTradeVO(
                    trade.id,
                    user.nickname,
                    trade.title,
                    trade.createdAt,
                    trade.imageUrl,
                    trade.userId.eq(userId)
                )
            ).from(trade)
            .join(user).on(trade.userId.eq(user.id))
            .fetch()

    override fun findById(tradeId: UUID): Trade? =
        tradeJpaRepository.findByIdOrNull(tradeId)

    override fun save(trade: Trade): Trade =
        tradeJpaRepository.save(trade)

    override fun delete(trade: Trade): Unit =
        tradeJpaRepository.delete(trade)
}