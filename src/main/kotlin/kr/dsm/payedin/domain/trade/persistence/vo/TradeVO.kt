package kr.dsm.payedin.domain.trade.persistence.vo

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import java.util.UUID

class TradeVO @QueryProjection constructor(
    val tradeId: UUID,
    val nickname: String,
    val title: String,
    val createdAt: LocalDateTime,
    val imageUrl: String
)