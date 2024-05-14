package kr.dsm.payedin.domain.wallet.presentation.dto

import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory

data class GetTransferHistoryResponse(
    val histories: List<TransferHistory>
)