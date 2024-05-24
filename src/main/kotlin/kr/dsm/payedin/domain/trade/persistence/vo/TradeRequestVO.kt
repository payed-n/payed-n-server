package kr.dsm.payedin.domain.trade.persistence.vo

import com.querydsl.core.annotations.QueryProjection
import kr.dsm.payedin.domain.trade.domain.value.TradeStatus
import java.util.*

class TradeRequestVO @QueryProjection constructor(
    val tradeRequestId: UUID,
    val tradeId: UUID,
    val imageUrl: String,
    val title: String,
    val userId: UUID,
    val nickname: String
)