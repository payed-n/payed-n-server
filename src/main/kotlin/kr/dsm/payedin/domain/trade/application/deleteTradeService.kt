package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.common.exception.ForbiddenException
import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class deleteTradeService(
    private val tradeRepository: TradeRepository
) {
    fun execute(tradeId: UUID) {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id

        val trade = this.tradeRepository.findById(tradeId)
        if(trade == null) throw NotFoundException("Trade with id $trade not found")
        if(trade.userId != userId) throw ForbiddenException("Trade with id $trade not allowed")

        this.tradeRepository.delete(trade)
    }
}