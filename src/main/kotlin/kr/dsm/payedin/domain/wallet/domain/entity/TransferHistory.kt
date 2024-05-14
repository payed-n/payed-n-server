package kr.dsm.payedin.domain.wallet.domain.entity

import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class TransferHistory(
    val balance: Int,

    val difference: Int,

    val name: String,

    val userId: UUID,
) : BaseEntity()