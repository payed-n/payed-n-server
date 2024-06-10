package kr.dsm.payedin.domain.trade.presentation.dto

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.trade.domain.value.TradeStatus
import kr.dsm.payedin.domain.user.domain.entity.User
import java.util.UUID

data class GetTradeInfoResponse(
    val trade: Trade,
    val user: User,
    val status: TradeStatus?,
    val tradeRequestId: UUID?
)