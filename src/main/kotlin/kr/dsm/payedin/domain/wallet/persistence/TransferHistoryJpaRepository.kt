package kr.dsm.payedin.domain.wallet.persistence

import kr.dsm.payedin.domain.wallet.domain.entity.TransferHistory
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TransferHistoryJpaRepository : CrudRepository<TransferHistory, UUID>