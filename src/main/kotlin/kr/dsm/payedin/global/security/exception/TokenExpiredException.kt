package kr.dsm.payedin.global.security.exception

import kr.dsm.payedin.common.exception.UnauthorizedException

object TokenExpiredException : UnauthorizedException("Token expired") {
    private fun readResolve(): Any = TokenExpiredException
}