package kr.dsm.payedin.thirdparty.api

import kr.dsm.payedin.thirdparty.api.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(name = "dms", url = "https://api.dms-dsm.com")
interface DmsClient {
    @GetMapping("/students/manager")
    fun getStudents(
        @RequestHeader("Authorization") token: String,
        @RequestParam("name") name: String,
        @RequestParam("sort") sort: String = "GCN"
    ): GetStudentsResponse

    @GetMapping("/students/{id}")
    fun getStudentDetail(
        @RequestHeader("Authorization") token: String,
        @PathVariable("id") id: UUID
    ): StudentDetailResponse

    @PostMapping("/auth/tokens")
    fun login(
        @RequestBody loginRequest: LoginRequest,
    ): LoginResponse

    @GetMapping("/students/profile")
    fun getProfile(
        @RequestHeader("Authorization") token: String
    ): StudentProfileResponse
}