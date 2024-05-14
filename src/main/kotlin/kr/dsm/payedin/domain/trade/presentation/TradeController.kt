package kr.dsm.payedin.domain.trade.presentation

import kr.dsm.payedin.domain.trade.application.GetAllTradeService
import kr.dsm.payedin.domain.trade.application.GetTradeInfoService
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/trades")
@RestController
class TradeController(
    private val getAllTradeService: GetAllTradeService,
    private val getTradeInfoService: GetTradeInfoService
) {

    @GetMapping
    fun getAllTrade(): GetAllTradeResponse =
        getAllTradeService.execute()

    @GetMapping("/{id}")
    fun getTradeInfo(@PathVariable(name = "id") tradeId: UUID) =
        getTradeInfoService.execute(tradeId)
}