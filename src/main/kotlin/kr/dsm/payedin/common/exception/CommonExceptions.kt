package kr.dsm.payedin.common.exception

import kr.dsm.payedin.global.exception.ExceptionStatus

open class CommonException(
    val status: Int,
    override val message: String
) : RuntimeException()

open class BadRequestException(
    override val message: String
) : CommonException(ExceptionStatus.BAD_REQUEST, message)

open class NotFoundException(
    override val message: String
) : CommonException(ExceptionStatus.NOT_FOUND, message)

open class UnauthorizedException(
    override val message: String
) : CommonException(ExceptionStatus.UNAUTHORIZED, message)

open class ForbiddenException(
    override val message: String
) : CommonException(ExceptionStatus.FORBIDDEN, message)

open class ConflictException(
    override val message: String
) : CommonException(ExceptionStatus.CONFLICT, message)
