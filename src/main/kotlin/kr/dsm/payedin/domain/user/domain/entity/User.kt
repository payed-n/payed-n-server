package kr.dsm.payedin.domain.user.domain.entity

import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class User(
    id: UUID? = null,

    val name: String,

    val gcn: String,

    val nickname: String,

    val profileImageUrl: String,

    val balance: Int,

    val accountNumber: String,

    val accountName: String,

    val bonusTotal: Int,

    val minusTotal: Int
) : BaseEntity(id)