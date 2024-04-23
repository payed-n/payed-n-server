package kr.dsm.payedin.domain.trade.persistence

import kr.dsm.payedin.domain.trade.domain.entity.Trade
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TradeJpaRepository : CrudRepository<Trade, UUID>