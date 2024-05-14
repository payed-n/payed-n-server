package kr.dsm.payedin.domain.wallet.domain.repository

import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory

interface TransferHistoryRepository {
    fun saveAll(transferHistory: List<TransferHistory>)
}