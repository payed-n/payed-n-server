package kr.dsm.payedin.thirdparty.api.dto

import java.util.UUID

data class GetStudentsResponse(
    val students: List<StudentResponse>
)

data class StudentResponse(
    val id: UUID,
    val name: String,
    val gcn: String
)

data class StudentDetailResponse(
    val id: UUID,
    val name: String,
    val bonus_point: Int,
    val minus_point: Int,
)