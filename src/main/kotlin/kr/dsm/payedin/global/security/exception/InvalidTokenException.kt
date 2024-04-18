package kr.dsm.payedin.global.security.exception

import kr.dsm.payedin.common.exception.UnauthorizedException

object InvalidTokenException : UnauthorizedException("Invalid token") {
    private fun readResolve(): Any = InvalidTokenException
}