package com.example.patro.resource

import com.example.patro.dto.DeviceRequest
import com.example.patro.dto.UserRequest
import com.example.patro.model.Device
import com.example.patro.model.User
import com.example.patro.repo.DeviceRepository
import com.example.patro.repo.UserRepository
import com.example.patro.service.DeviceService
import com.example.patro.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class ApiControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Mock
    private lateinit var deviceService: DeviceService

    @Mock
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var deviceRepository: DeviceRepository

    @Test
    fun `test create device endpoint`() {
        val device = DeviceRequest(serialNumber = "123456",  phoneNumber = "123-456-7890", model = "Model X")


        mockMvc.perform(
            post("/api/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(device))
        )
            .andExpect(status().isOk())
    }

    @Test
    fun `test create user endpoint`() {
        val user = UserRequest(firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))


            mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        )
            .andExpect(status().isOk())
    }

    @Test
    fun `test assign device to user endpoint`() {
        val device = Device( serialNumber = "123456", uuid = UUID.randomUUID(), phoneNumber = "123-456-7890", model = "Model X")
        val user = User( firstName = "Joy", lastName = "Doe", address = "1234 Main St", birthday = LocalDate.of(1990, 5, 15))

       val dev = deviceRepository.saveAndFlush(device)
       val use = userRepository.saveAndFlush(user)

        mockMvc.perform(
            post("/api/users/${use.id}/assign-device/${dev.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(user.id))
            .andExpect(jsonPath("$.device.id").value(device.id))
    }

    @Test
    fun `test assign device to user endpoint with invalid user`() {
        val device = Device( serialNumber = "123456", uuid = UUID.randomUUID(), phoneNumber = "123-456-7890", model = "Model X")

        val dev = deviceRepository.saveAndFlush(device)


        mockMvc.perform(
            post("/api/users/999/assign-device/${dev.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound())
    }

    @Test
    fun `test assign device to user endpoint with invalid device`() {
        val user = User( firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))

        val use = userRepository.saveAndFlush(user)

        mockMvc.perform(
            post("/api/users/${use.id}/assign-device/999")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound())
    }

    @Test
    fun `test get all users with devices`() {
        val device1 = Device( serialNumber = "123456", uuid = UUID.randomUUID(), phoneNumber = "123-456-7890", model = "Model X")
        val device2 = Device( serialNumber = "789012", uuid = UUID.randomUUID(), phoneNumber = "987-654-3210", model = "Model Y")
        val user1 = User( firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))
        val user2 = User( firstName = "Jane", lastName = "Smith", address = "456 Elm St", birthday = LocalDate.of(1985, 10, 20))

        user1.device = device1
        user2.device = device2

       deviceRepository.saveAllAndFlush(listOf(device1,device2))
        userRepository.saveAllAndFlush(listOf( user1,user2))

        mockMvc.perform(
            get("/api/users-with-devices")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(user1.id))
            .andExpect(jsonPath("$[0].device.id").value(device1.id))
            .andExpect(jsonPath("$[1].id").value(user2.id))
            .andExpect(jsonPath("$[1].device.id").value(device2.id))
    }
}
