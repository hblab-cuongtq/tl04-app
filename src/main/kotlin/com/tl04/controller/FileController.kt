package com.tl04.controller

import com.tl04.service.S3Service
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/files")
class FileController(
    private val s3Service: S3Service,
) {
    @PostMapping("/upload")
    fun upload(@RequestParam file: MultipartFile): Map<String, String> {
        val key = s3Service.upload(file)
        return mapOf("key" to key)
    }

    @GetMapping("/download")
    fun download(@RequestParam key: String): ResponseEntity<ByteArray> {
        val data = s3Service.download(key)

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"${key.substringAfterLast("/")}\""
            )
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(data)
    }
}
