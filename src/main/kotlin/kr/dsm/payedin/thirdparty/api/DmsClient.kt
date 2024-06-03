package kr.dsm.payedin.thirdparty.api

import kr.dsm.payedin.thirdparty.api.dto.GetStudentsResponse
import kr.dsm.payedin.thirdparty.api.dto.StudentDetailResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

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
        @PathVariable("id") id: String
    ): StudentDetailResponse
}