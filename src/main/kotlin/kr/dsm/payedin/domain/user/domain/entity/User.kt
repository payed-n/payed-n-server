package kr.dsm.payedin.domain.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import kr.dsm.payedin.common.exception.BadRequestException
import kr.dsm.payedin.global.entity.BaseEntity

@Entity
class User(
    val name: String,

    @Column(unique = true)
    val gcn: String,

    @Column(unique = true)
    val nickname: String,

    var balance: Int,

    val accountNumber: String,

    val bonusTotal: Int,

    val minusTotal: Int
) : BaseEntity() {
    fun withdraw(amount: Int) {
        if (this.balance < amount) {
            throw BadRequestException("Invalid Amount")
        }

        this.balance -= amount
    }

    fun deposit(amount: Int) {
        this.balance += amount
    }
}