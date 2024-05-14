package kr.dsm.payedin.domain.wallet.presentation

import kr.dsm.payedin.domain.wallet.application.TransferService
import kr.dsm.payedin.domain.wallet.presentation.dto.TransferRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/wallets")
@RestController
class WalletController(
    private val transferService: TransferService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transfer")
    fun transfer(@RequestBody request: TransferRequest) {
        transferService.execute(request)
    }
}