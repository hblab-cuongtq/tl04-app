package com.tl04.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
@RequestMapping("/ping")
class PingController(
    private val restClient: RestClient,
) {
    @GetMapping
    fun callServiceB(): ResponseEntity<String> {
        return try {
            val response = restClient.get()
                .uri("http://tl04-alb-dev-705943374.ap-southeast-1.elb.amazonaws.com/service-b/service")
                .retrieve()
                .body(String::class.java)

            ResponseEntity.ok("OK 👉 $response")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("FAIL ❌ ${e.message}")
        }
    }
}
