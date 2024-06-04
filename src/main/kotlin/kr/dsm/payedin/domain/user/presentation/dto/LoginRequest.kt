package kr.dsm.payedin.domain.user.presentation.dto

data class LoginRequest(
    val accountId: String,
    val password: String
)
