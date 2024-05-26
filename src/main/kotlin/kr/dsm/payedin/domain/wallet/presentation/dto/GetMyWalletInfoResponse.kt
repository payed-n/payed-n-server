package kr.dsm.payedin.domain.wallet.presentation.dto

data class GetMyWalletInfoResponse(
    val balance: Int,
    val accountNumber: String,
    val name: String
)
