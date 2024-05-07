package kr.dsm.payedin.domain.trade.domain.entity

import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class Trade(
    val title: String,

    val content: String,

    val price: String,

    val imageUrl: String,

    val userId: UUID
) : BaseEntity()