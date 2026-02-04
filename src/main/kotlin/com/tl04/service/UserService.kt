package com.tl04.service

import com.tl04.dto.UserDTO
import com.tl04.entity.User
import com.tl04.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository) {
    fun create(userRequest: UserDTO): ResponseEntity<String> {
        val user = User(
            name = userRequest.name,
            email = userRequest.email,
        )
        userRepository.save(user)
        return ResponseEntity.ok("Created ${user.name} successful")
    }

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): Optional<User> = userRepository.findById(id)

    fun delete(id: Long): ResponseEntity<String> {
        userRepository.deleteById(id)
        return ResponseEntity.ok("Deleted successful")
    }

    fun update(id: Long, newData: UserDTO): ResponseEntity<String> {
        val oldUser = userRepository.findById(id)
            .orElseThrow {
                RuntimeException("User not found: $id")
            }
        newData.name?.let {
            oldUser.name = it
        }
        newData.email?.let {
            oldUser.email = it
        }
        userRepository.save(oldUser)
        return ResponseEntity.ok("Updated ${oldUser.name} successful")
    }
}
