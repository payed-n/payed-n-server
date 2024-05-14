package kr.dsm.payedin.domain.wallet.application

import kr.dsm.payedin.domain.wallet.domain.repository.TransferHistoryRepository
import kr.dsm.payedin.domain.wallet.presentation.dto.GetTransferHistoryResponse
import kr.dsm.payedin.global.security.auth.CustomUserDetail
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetTransferHistoryService(
    private val transferHistoryRepository: TransferHistoryRepository
) {

    fun execute(): GetTransferHistoryResponse {
        val currentUserId =
            (SecurityContextHolder.getContext().authentication.principal as CustomUserDetail).user.id

        return GetTransferHistoryResponse(
            transferHistoryRepository.findByUserId(currentUserId)
        )
    }
}