package kr.dsm.payedin.domain.user.presentation

import kr.dsm.payedin.domain.user.application.GetPointService
import kr.dsm.payedin.domain.user.application.GetUsernameService
import kr.dsm.payedin.domain.user.application.LoginService
import kr.dsm.payedin.domain.user.presentation.dto.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val loginService: LoginService,
    private val getPointService: GetPointService,
    private val getUsernameService: GetUsernameService
) {

    @PostMapping("/sign-in")
    fun login(@RequestBody request: LoginRequest): TokenResponse =
        loginService.execute(request)

    @GetMapping("/point")
    fun getPoint(): GetPointResponse =
        getPointService.execute()

    @GetMapping
    fun getUserName(@RequestParam(name = "account_number") accountNumber: String): GetUsernameResponse =
        getUsernameService.execute(accountNumber)

}