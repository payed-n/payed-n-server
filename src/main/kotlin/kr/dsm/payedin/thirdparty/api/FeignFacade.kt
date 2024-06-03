package kr.dsm.payedin.thirdparty.api

import kr.dsm.payedin.domain.user.exception.UserNotFoundException
import kr.dsm.payedin.thirdparty.api.dto.StudentDetailResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FeignFacade(
    private val dmsClient: DmsClient,
    @Value("\${dms.token}")
    private val apiKey: String
) {

    fun getDmsUserInfo(name: String, gcn: String): StudentDetailResponse {
        var id: UUID = UUID(0, 0)
        dmsClient.getStudents(apiKey, name).students
            .forEach {
                if (it.gcn == gcn) {
                    id = it.id
                }
            }

        if (id == UUID(0, 0)) {
            throw UserNotFoundException
        }

        return dmsClient.getStudentDetail(apiKey, id.toString())
    }

    fun getDmsUserInfo(dmsId: UUID): StudentDetailResponse {
        return dmsClient.getStudentDetail(apiKey, dmsId.toString())
    }
}