package kr.dsm.payedin.domain.user.domain.repository

import kr.dsm.payedin.domain.user.domain.entity.User
import java.util.UUID

interface UserRepository {
    fun findById(userId: UUID): User?

    fun existsByGcnOrNickname(gcn: String, nickname: String): Boolean

    fun save(user: User): User

    fun findByGcn(gcn: String): User?

    fun findByAccountNumber(accountNumber: String): User?

    fun findAll(): List<User>

    fun findByNickname(nickname: String): User?
}