package com.tl04.controller

import com.tl04.dto.UserDTO
import com.tl04.entity.User
import com.tl04.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {
    @PostMapping
    fun create(@RequestBody user: UserDTO): User = userService.create(user)

    @GetMapping
    fun findAll(): List<User> = userService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<User> = userService.findById(id)

    @DeleteMapping
    fun delete(id: Long) = userService.delete(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody userDTO: UserDTO): User {
        return userService.update(id, userDTO)
    }
}
