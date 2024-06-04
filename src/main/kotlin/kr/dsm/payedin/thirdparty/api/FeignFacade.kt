package kr.dsm.payedin.thirdparty.api

import kr.dsm.payedin.domain.user.exception.UserNotFoundException
import kr.dsm.payedin.thirdparty.api.dto.LoginRequest
import kr.dsm.payedin.thirdparty.api.dto.StudentDetailResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class FeignFacade(
    private val dmsClient: DmsClient,
    @Value("\${dms.token}")
    private val apiKey: String
) {

    fun getDmsUserInfo(name: String, gcn: String): StudentDetailResponse {
        dmsClient.getStudents(apiKey, name).students
            .forEach {
                if (it.gcn == gcn) {
                    return dmsClient.getStudentDetail(apiKey, it.id)
                }
            }
        throw UserNotFoundException
    }

    fun login(accountId: String, password: String): StudentDetailResponse {
        val token = dmsClient.login(
            LoginRequest(accountId, password)
        ).access_token

        val profile = dmsClient.getProfile(token)

        return StudentDetailResponse(
            name = profile.name,
            gcn = profile.gcn,
            bonus_point = profile.bonus_point,
            minus_point = profile.minus_point
        )
    }
}