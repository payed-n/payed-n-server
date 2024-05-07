package kr.dsm.payedin.domain.user.application

import kr.dsm.payedin.domain.user.presentation.dto.GetPointResponse
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetPointService {

    fun execute(): GetPointResponse {
        val currentUser =
            (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user

        return GetPointResponse(
            plusPoint = currentUser.bonusTotal,
            minusPoint = currentUser.minusTotal
        )
    }
}