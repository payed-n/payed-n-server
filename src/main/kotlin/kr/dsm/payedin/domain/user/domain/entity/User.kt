package kr.dsm.payedin.domain.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class User(
    val name: String,

    @Column(unique = true)
    val gcn: String,

    @Column(unique = true)
    val nickname: String,

    val balance: Int,

    val accountNumber: String,

    val bonusTotal: Int,

    val minusTotal: Int
) : BaseEntity()