package com.example.patro.service

import com.example.patro.repo.UserRepository
import com.example.patro.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun getAllUsersWithDevices(): List<User> {
        return userRepository.findAll()
    }
    fun getUserById(userId: Long): User? {
        return userRepository.findById(userId).orElseGet { null }
    }

    fun saveUser(user: User): User {
        return userRepository.saveAndFlush(user)
    }
}