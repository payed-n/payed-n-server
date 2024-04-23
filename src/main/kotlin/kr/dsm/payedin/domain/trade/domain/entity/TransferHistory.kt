package kr.dsm.payedin.domain.trade.domain.entity

import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class TransferHistory(
    id: UUID? = null,

    val balance: Int,

    val difference: Int,

    val userId: UUID,

    val targetUserId: UUID
) : BaseEntity(id)