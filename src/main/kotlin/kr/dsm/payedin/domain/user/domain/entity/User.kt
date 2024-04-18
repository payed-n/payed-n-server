package kr.dsm.payedin.domain.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class User(
    id: UUID? = null,

    @Column(nullable = false, columnDefinition = "VARCHAR(12)")
    val name: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(5)", unique = true)
    val gcn: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(20)", unique = true)
    val nickname: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    val profileImageUrl: String,

    @Column(nullable = false, columnDefinition = "INTEGER")
    val balance: Int,

    @Column(nullable = false, columnDefinition = "VARCHAR(12)", unique = true)
    val accountNumber: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    val accountName: String,

    @Column(nullable = false, columnDefinition = "INTEGER")
    val bonusTotal: Int,

    @Column(nullable = false, columnDefinition = "INTEGER")
    val minusTotal: Int
) : BaseEntity(id)