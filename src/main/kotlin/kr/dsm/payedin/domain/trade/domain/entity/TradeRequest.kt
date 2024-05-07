package kr.dsm.payedin.domain.trade.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import kr.dsm.payedin.domain.trade.domain.value.TradeStatus
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class TradeRequest(
    val tradeId: UUID,

    val userId: UUID,

    @Enumerated(EnumType.STRING)
    var status: TradeStatus,
) : BaseEntity()