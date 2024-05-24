package kr.dsm.payedin.domain.trade.presentation.dto

import kr.dsm.payedin.domain.trade.persistence.vo.TradeRequestVO

data class GetAllTradeRequestResponse (
    val requests: List<TradeRequestVO>
)