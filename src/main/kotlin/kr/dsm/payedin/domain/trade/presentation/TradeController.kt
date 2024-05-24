package kr.dsm.payedin.domain.trade.presentation

import kr.dsm.payedin.domain.trade.application.*
import kr.dsm.payedin.domain.trade.presentation.dto.ApproveTradeRequest
import kr.dsm.payedin.domain.trade.presentation.dto.CreateTradeRequest
import kr.dsm.payedin.domain.trade.presentation.dto.GetAllTradeResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/trades")
@RestController
class TradeController(
    private val getAllTradeService: GetAllTradeService,
    private val getTradeInfoService: GetTradeInfoService,
    private val createTradeService: CreateTradeService,
    private val deleteTradeService: DeleteTradeService,
    private val createTradeRequestService: CreateTradeRequestService,
    private val getAllTradeRequestService: GetAllTradeRequestService,
    private val approveTradeRequestService: ApproveTradeRequestService,
) {

    @GetMapping
    fun getAllTrade(): GetAllTradeResponse =
        getAllTradeService.execute()

    @GetMapping("/{id}")
    fun getTradeInfo(@PathVariable(name = "id") tradeId: UUID) =
        getTradeInfoService.execute(tradeId)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createTrade(@RequestBody() request: CreateTradeRequest) =
        createTradeService.execute(request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteTrade(@PathVariable(name = "id") tradeId: UUID) =
        deleteTradeService.execute(tradeId)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/trade/{id}")
    fun createTradeRequest(@PathVariable(name = "id") tradeId: UUID) =
        createTradeRequestService.execute(tradeId)

    @GetMapping("/trade/req")
    fun getAllTradeRequest() =
        getAllTradeRequestService.execute()

    @PostMapping("/approve")
    fun approveTradeRequest(
        @RequestParam(name = "trade_request_id") tradeRequestId: UUID,
        @RequestParam(name = "approve") approve: Boolean,
    ) {
        approveTradeRequestService.execute(tradeRequestId, approve)
    }
}