package kr.dsm.payedin.domain.trade.persistence.vo

import com.querydsl.core.annotations.QueryProjection
import kr.dsm.payedin.domain.trade.domain.value.TradeStatus
import java.util.*

class TradeRequestVO @QueryProjection constructor(
    val tradeId: UUID,
    val userId: UUID,
    val status: TradeStatus
)