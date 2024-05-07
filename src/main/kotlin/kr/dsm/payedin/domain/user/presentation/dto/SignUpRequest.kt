package kr.dsm.payedin.domain.user.presentation.dto

import jakarta.validation.constraints.Size

data class SignUpRequest(
    val gcn: String,
    val name: String,
    val nickname: String,
)
