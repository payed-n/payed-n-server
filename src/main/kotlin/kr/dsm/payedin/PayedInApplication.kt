package kr.dsm.payedin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PayedInApplication

fun main(args: Array<String>) {
    runApplication<PayedInApplication>(*args)
}
