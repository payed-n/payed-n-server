package kr.dsm.payedin.global.scheduler

import jakarta.transaction.Transactional
import kr.dsm.payedin.domain.user.domain.repository.UserRepository
import kr.dsm.payedin.thirdparty.api.FeignFacade
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PointScheduler(
    private val feignFacade: FeignFacade,
    private val userRepository: UserRepository
) {

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    fun run() {
        userRepository.findAll().forEach {
            val dmsInfo = feignFacade.getDmsUserInfo(it.name, it.gcn)
            it.updatePointInfo(
                bonus = dmsInfo.bonus_point,
                minus = dmsInfo.minus_point
            )

            userRepository.save(it)
        }
    }
}