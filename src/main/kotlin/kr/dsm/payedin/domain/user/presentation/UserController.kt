package kr.dsm.payedin.domain.user.presentation

import kr.dsm.payedin.domain.user.application.GetPointService
import kr.dsm.payedin.domain.user.application.GetUsernameService
import kr.dsm.payedin.domain.user.application.LoginService
import kr.dsm.payedin.domain.user.application.SignUpService
import kr.dsm.payedin.domain.user.presentation.dto.GetPointResponse
import kr.dsm.payedin.domain.user.presentation.dto.GetUsernameResponse
import kr.dsm.payedin.domain.user.presentation.dto.SignUpRequest
import kr.dsm.payedin.domain.user.presentation.dto.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val loginService: LoginService,
    private val signUpService: SignUpService,
    private val getPointService: GetPointService,
    private val getUsernameService: GetUsernameService
) {

    @GetMapping("/sign-in")
    fun login(@RequestParam(name = "gcn") gcn: String): TokenResponse =
        loginService.execute(gcn)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): TokenResponse =
        signUpService.execute(request)

    @GetMapping("/point")
    fun getPoint(): GetPointResponse =
        getPointService.execute()

    @GetMapping
    fun getUserName(@RequestParam(name = "account_number") accountNumber: String): GetUsernameResponse =
        getUsernameService.execute(accountNumber)

}