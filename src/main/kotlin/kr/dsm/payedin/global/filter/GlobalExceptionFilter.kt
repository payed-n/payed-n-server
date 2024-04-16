package kr.dsm.payedin.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.dsm.payedin.common.exception.CommonException
import kr.dsm.payedin.global.exception.ExceptionResponse
import kr.dsm.payedin.global.exception.InternalServerError
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CommonException) {
            response.writeExceptionResponse(e)
        } catch (e: Exception) {
            when (e.cause) {
                is CommonException -> {
                    logger.warn("${e.cause} on ${request.requestURI}")
                    response.writeExceptionResponse(e.cause as CommonException)
                }
                else -> {
                    logger.error(e.stackTrace)
                    response.writeExceptionResponse(InternalServerError)
                }
            }
        }
    }

    fun HttpServletResponse.writeExceptionResponse(e: CommonException) {
        this.status = e.status
        this.writer.write(
            objectMapper.writeValueAsString(
                ExceptionResponse(
                    message = e.message,
                    status = e.status
                )
            )
        )
    }
}