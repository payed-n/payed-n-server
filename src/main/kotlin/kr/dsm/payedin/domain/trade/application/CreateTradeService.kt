package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.presentation.dto.CreateTradeRequest
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CreateTradeService (
    private val tradeRepository: TradeRepository,
) {

    fun execute(request: CreateTradeRequest) {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id

        this.tradeRepository.save(
            Trade(
                title = request.title,
                content = request.content,
                price = request.price,
                imageUrl = request.imageUrl,
                userId = userId,
            )
        )
    }
}