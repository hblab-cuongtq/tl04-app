package com.tl04.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class S3Service(
    private val s3Client: S3Client
) {
    private val bucket = "tl04-s3-bucket"

    fun upload(file: MultipartFile): String {
        val key = "uploads/${System.currentTimeMillis()}_${file.originalFilename}"

        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.contentType)
            .build()

        s3Client.putObject(
            request,
            RequestBody.fromBytes(file.bytes)
        )

        return key
    }

    fun download(key: String): ByteArray {
        val request = GetObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        return s3Client.getObject(request).readAllBytes()
    }
}
