package com.tl04.service

import com.tl04.dto.UserDTO
import com.tl04.entity.User
import com.tl04.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class UserService (private val userRepository: UserRepository) {
    fun create(userRequest: UserDTO): User {
        val user = User(
            name = userRequest.name,
            email = userRequest.email,
        )
        return userRepository.save(user)
    }

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): Optional<User> = userRepository.findById(id)

    fun delete(id: Long) = userRepository.deleteById(id)

    fun update(id: Long, newData: UserDTO): User {
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

        return userRepository.save(oldUser)
    }
}
