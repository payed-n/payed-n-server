package kr.dsm.payedin.domain.user.exception

import kr.dsm.payedin.common.exception.NotFoundException

object UserNotFoundException : NotFoundException("User not found") {
    private fun readResolve(): Any = UserNotFoundException
}