package kr.dsm.payedin.domain.trade.presentation.dto

import kr.dsm.payedin.domain.trade.persistence.vo.TradeVO

data class GetAllTradeResponse(
    val trade: List<TradeVO>
)