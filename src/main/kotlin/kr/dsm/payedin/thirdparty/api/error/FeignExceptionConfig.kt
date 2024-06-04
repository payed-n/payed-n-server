package kr.dsm.payedin.thirdparty.api.error

import feign.Response
import feign.codec.ErrorDecoder
import kr.dsm.payedin.common.exception.BadRequestException
import kr.dsm.payedin.common.exception.ForbiddenException
import kr.dsm.payedin.common.exception.NotFoundException
import kr.dsm.payedin.common.exception.UnauthorizedException
import kr.dsm.payedin.global.exception.InternalServerError
import java.lang.Exception

class FeignExceptionConfig : ErrorDecoder{
    override fun decode(methodKey: String, response: Response): Exception {
        return when (response.status()) {
            400 -> BadRequestException(response.reason())
            401 -> UnauthorizedException(response.reason())
            403 -> ForbiddenException(response.reason())
            404 -> NotFoundException(response.reason())
            else -> InternalServerError
        }
    }
}