package kr.dsm.payedin.domain.user.persistence

import kr.dsm.payedin.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserJpaRepository : CrudRepository<User, UUID> {
    fun findByGcn(gcn: String): User?

    fun existsByGcnOrNickname(gcn: String, nickname: String): Boolean

    fun findByAccountNumber(accountNumber: String): User?

    override fun findAll(): List<User>

    fun findByNickname(nickname: String): User?
}