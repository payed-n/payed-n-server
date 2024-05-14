package kr.dsm.payedin.domain.trade.application

import kr.dsm.payedin.domain.trade.domain.repository.TradeRepository
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetAllTradeService(
    private val tradeRepository: TradeRepository
) {

    fun execute(): GetAllTradeResponse =
        GetAllTradeResponse(
            tradeRepository.findAll()
        )
}