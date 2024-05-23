package kr.dsm.payedin.domain.trade.presentation.dto

import java.util.UUID

data class ApproveTradeRequest(
    val tradeId: UUID,
    val userId: UUID,
    val approve: Boolean
)
