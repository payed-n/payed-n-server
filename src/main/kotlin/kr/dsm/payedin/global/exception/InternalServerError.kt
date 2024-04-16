package kr.dsm.payedin.global.exception

import kr.dsm.payedin.common.exception.CommonException

object InternalServerError : CommonException(ExceptionStatus.INTERNAL_SERVER_ERROR, "Internal Server Error") {
    private fun readResolve(): Any = InternalServerError
}