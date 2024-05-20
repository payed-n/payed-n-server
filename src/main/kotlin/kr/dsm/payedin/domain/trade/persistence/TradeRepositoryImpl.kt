package kr.dsm.payedin.domain.trade.persistence

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
    override fun findAll(): List<TradeVO> =
        queryFactory
            .select(
                QTradeVO(
                    trade.id,
                    user.nickname,
                    trade.title,
                    trade.createdAt,
                    trade.imageUrl
                )
            ).from(trade)
            .join(user).on(trade.userId.eq(user.id))
            .fetch()

    override fun findById(tradeId: UUID): Trade? =
        tradeJpaRepository.findByIdOrNull(tradeId)

    override fun save(trade: Trade): Trade =
        tradeJpaRepository.save(trade)
}