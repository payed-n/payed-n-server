package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.BadRequestException
import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.domain.repository.TradeRequestRepository
import kr.dsm.payedin.domain.trade.presentation.dto.ApproveTradeRequest
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApproveTradeRequestService(
    val userRepository: UserRepository,
    val transferHistoryRequestRepository: TradeRequestRepository,
    val tradeRequestRepository: TradeRequestRepository,
    val tradeRepository: TradeRepository
) {
    @Transactional
    fun execute(tradeId: UUID, userId: UUID, approve: Boolean) {
        val buyUser = userRepository.findById(userId) ?: throw NotFoundException("User with id $userId not found")
        val trade = tradeRepository.findById(tradeId) ?: throw NotFoundException("Trade with id $tradeId not found")
        val tradeRequest = tradeRequestRepository.findByUserIdAndTradeId(userId, tradeId) ?: throw NotFoundException("Trade with id $tradeId not found")
        val saleUser = userRepository.findById(trade.userId) ?: throw NotFoundException("User with id $tradeId not found")

        if(approve) {
            if(buyUser.balance < trade.price) throw BadRequestException("잔액 부족")
            buyUser.balance -= trade.price
            saleUser.balance += trade.price
            userRepository.save(buyUser)
            userRepository.save(saleUser)
            transferHistoryRequestRepository.save(tradeRequest)
            tradeRepository.delete(trade)
        }

        tradeRequestRepository.delete(tradeRequest)
    }
}