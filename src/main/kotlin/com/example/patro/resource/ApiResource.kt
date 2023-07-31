package com.example.patro.resource

import com.example.patro.dto.DeviceRequest
import com.example.patro.dto.UserRequest
import com.example.patro.model.Device
import com.example.patro.model.User
import com.example.patro.service.DeviceService
import com.example.patro.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api")
class ApiResource(private val deviceService: DeviceService, private val userService: UserService) {

    @PostMapping("/devices")
    fun createDevice(@RequestBody deviceDto: DeviceRequest): Device {
        return deviceService.createDevice(
            Device(
                model = deviceDto.model,
                uuid = UUID.randomUUID(),
                phoneNumber = deviceDto.phoneNumber,
                serialNumber = deviceDto.serialNumber
            )
        )
    }

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserRequest): User {
        return userService.createUser(
            User(
                firstName = userDto.firstName,
                lastName = userDto.lastName,
                address = userDto.address,
                birthday = userDto.birthday
            )
        )
    }

    @PostMapping("/users/{userId}/assign-device/{deviceId}")
    fun assignDeviceToUser(
        @PathVariable userId: Long,
        @PathVariable deviceId: Long
    ): User {
        val device = deviceService.getDeviceById(deviceId) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,"Device not found")

        val user = userService.getUserById(userId)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found")

        user.device = device
        userService.saveUser(user)

        return user
    }

    @GetMapping("/users-with-devices")
    fun getUsersWithDevices(): List<User> {
        return userService.getAllUsersWithDevices()
    }
}