package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.wallet.domain.repository.TransferHistoryRepository
import kr.dsm.payedin.domain.wallet.domain.service.WalletDomainService
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class ApproveTradeRequestService(
    val userRepository: UserRepository,
    val tradeRequestRepository: TradeRequestRepository,
    val tradeRepository: TradeRepository,
    val transferHistoryRepository: TransferHistoryRepository,
    val walletDomainService: WalletDomainService
) {

    fun execute(tradeRequestId: UUID, approve: Boolean) {
        val tradeRequest = tradeRequestRepository.findById(tradeRequestId)
            ?: throw NotFoundException("TradeRequest with id $tradeRequestId not found")
        val trade = tradeRepository.findById(tradeRequest.tradeId)!!
        val currentUser = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user
        val targetUser = userRepository.findById(tradeRequest.userId)!!

        if (approve) {
            transferHistoryRepository.saveAll(
                walletDomainService.transfer(currentUser, targetUser, trade.price)
            )
            userRepository.save(currentUser)
            tradeRepository.delete(trade)
        }

        tradeRequestRepository.delete(tradeRequest)
    }
}