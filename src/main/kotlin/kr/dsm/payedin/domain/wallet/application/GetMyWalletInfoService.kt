package kr.dsm.payedin.domain.wallet.application

import kr.dsm.payedin.domain.wallet.presentation.dto.GetMyWalletInfoResponse
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetMyWalletInfoService {

    fun execute(): GetMyWalletInfoResponse {
        val currentWallet =
            (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user

        return GetMyWalletInfoResponse(
            balance = currentWallet.balance,
            accountNumber = currentWallet.accountNumber,
            name = currentWallet.name
        )
    }
}