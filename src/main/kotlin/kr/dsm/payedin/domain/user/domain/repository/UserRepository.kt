package kr.dsm.payedin.domain.user.domain.repository

import kr.dsm.payedin.domain.user.domain.entity.User
import java.util.UUID

interface UserRepository {
    fun findById(userId: UUID): User?

    fun save(user: User): User
}