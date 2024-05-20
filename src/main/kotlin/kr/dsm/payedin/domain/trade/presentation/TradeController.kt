package kr.dsm.payedin.domain.trade.presentation

import kr.dsm.payedin.domain.trade.application.CreateTradeService
import kr.dsm.payedin.domain.trade.application.GetAllTradeService
import kr.dsm.payedin.domain.trade.application.GetTradeInfoService
import kr.dsm.payedin.domain.trade.presentation.dto.CreateTradeRequest
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/trades")
@RestController
class TradeController(
    private val getAllTradeService: GetAllTradeService,
    private val getTradeInfoService: GetTradeInfoService,
    private val createTradeService: CreateTradeService,
) {

    @GetMapping
    fun getAllTrade(): GetAllTradeResponse =
        getAllTradeService.execute()

    @GetMapping("/{id}")
    fun getTradeInfo(@PathVariable(name = "id") tradeId: UUID) =
        getTradeInfoService.execute(tradeId)

    @PostMapping
    fun createTrade(@RequestBody() request: CreateTradeRequest) =
        createTradeService.execute(request);
}