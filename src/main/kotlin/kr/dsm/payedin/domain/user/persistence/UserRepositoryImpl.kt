package kr.dsm.payedin.domain.user.persistence

import kr.dsm.payedin.domain.user.domain.entity.User
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun findById(userId: UUID): User? =
        userJpaRepository.findByIdOrNull(userId)

    override fun save(user: User): User =
        userJpaRepository.save(user)
}