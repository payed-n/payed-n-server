package kr.dsm.payedin.domain.trade.persistence

import org.springframework.stereotype.Repository

@Repository
class TradeRepositoryImpl(
    private val tradeJpaRepository: TradeJpaRepository
){
}