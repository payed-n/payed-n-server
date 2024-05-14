package kr.dsm.payedin.domain.wallet.presentation.dto

data class TransferRequest(
    val targetAccountNumber: String,
    val amount: Int
)