package kr.dsm.payedin.domain.trade.presentation.dto

data class CreateTradeRequest(
    val imageUrl: String,
    val title: String,
    val content: String,
    val price: Int,
)