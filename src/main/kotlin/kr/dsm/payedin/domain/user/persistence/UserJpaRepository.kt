package kr.dsm.payedin.domain.user.persistence

import kr.dsm.payedin.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserJpaRepository : CrudRepository<User, UUID>