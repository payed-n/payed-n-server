package kr.dsm.payedin.domain.wallet.application

import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.wallet.domain.repository.TransferHistoryRepository
import kr.dsm.payedin.domain.wallet.domain.service.WalletDomainService
import kr.dsm.payedin.domain.wallet.presentation.dto.TransferRequest
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TransferService(
    private val walletDomainService: WalletDomainService,
    private val userRepository: UserRepository,
    private val transferHistoryRepository: TransferHistoryRepository
) {

    fun execute(request: TransferRequest) {
        val currentWallet =
            (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user
        val target = userRepository.findByAccountNumber(request.targetAccountNumber)
            ?: throw NotFoundException("Wallet Not Found")

        transferHistoryRepository.saveAll(
            walletDomainService.transfer(currentWallet, target, request.amount)
        )
        userRepository.save(target)
        userRepository.save(currentWallet)
    }
}