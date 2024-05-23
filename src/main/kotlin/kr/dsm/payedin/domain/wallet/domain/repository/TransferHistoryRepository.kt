package kr.dsm.payedin.domain.wallet.domain.repository

import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory
import java.util.UUID

interface TransferHistoryRepository {
    fun saveAll(transferHistory: List<TransferHistory>)

    fun findByUserId(userId: UUID): List<TransferHistory>

    fun save(transferHistory: TransferHistory): TransferHistory
}