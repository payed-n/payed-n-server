package kr.dsm.payedin.domain.wallet.domain.service

import kr.dsm.payedin.domain.user.domain.entity.User
import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory
import org.springframework.stereotype.Component

@Component
class WalletDomainService {
    fun transfer(wallet: User, targetWallet: User, amount: Int): List<TransferHistory> {
        wallet.withdraw(amount)
        targetWallet.deposit(amount)

        val from = TransferHistory(
            balance = wallet.balance,
            difference = -1 * amount,
            name = targetWallet.name,
            userId = wallet.id
        )

        val to = TransferHistory(
            balance = targetWallet.balance,
            difference = amount,
            name = wallet.name,
            userId = targetWallet.id
        )

        return listOf(from, to)
    }
}