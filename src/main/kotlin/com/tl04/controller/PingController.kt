package com.tl04.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/ping")
class PingController(
    private val restTemplate: RestTemplate,
    @Value("SERVICE_URL")
    private val serviceBUrl: String
) {
    @GetMapping
    fun callServiceB(): ResponseEntity<String> {
        return try {
            val response = restTemplate.getForEntity(
                "$serviceBUrl/service",
                String::class.java
            )
            ResponseEntity.ok("OK 👉 ${response.body}")
        } catch (e: Exception) {
            ResponseEntity.status(500)
                .body("FAIL ❌ ${e.message}")
        }
    }
}
