package kr.dsm.payedin.domain.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import kr.dsm.payedin.common.exception.BadRequestException
import kr.dsm.payedin.global.entity.BaseEntity
import java.util.UUID

@Entity
class User(
    val name: String,

    @Column(unique = true)
    val gcn: String,

    @Column(unique = true)
    val nickname: String,

    var balance: Int,

    val accountNumber: String,

    var bonusTotal: Int,

    var minusTotal: Int
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

    fun updatePointInfo(bonus: Int, minus: Int) {
        this.bonusTotal = bonus
        this.minusTotal = minus

        this.balance = (bonusTotal - bonus) * 1000 - (minusTotal - minus) * 500
    }
}