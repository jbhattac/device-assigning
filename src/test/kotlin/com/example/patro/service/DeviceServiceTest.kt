package com.example.patro.service

import com.example.patro.model.Device
import com.example.patro.repo.DeviceRepository
import com.example.patro.service.DeviceService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

class DeviceServiceTest {

    @Mock
    private lateinit var deviceRepository: DeviceRepository

    @InjectMocks
    private lateinit var deviceService: DeviceService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateDevice() {
        val givenUuid = UUID.randomUUID()
        val device = Device(serialNumber = "123456", phoneNumber = "123-456-7890",uuid =givenUuid, model = "Model X")
        val savedDevice = Device(id = 1, serialNumber = "123456", phoneNumber = "123-456-7890",uuid =givenUuid, model = "Model X")

        `when`(deviceRepository.save(device)).thenReturn(savedDevice)

        val result = deviceService.createDevice(device)

        assertEquals(savedDevice, result)
    }

    @Test
    fun testGetAllDevices() {
        val device1 = Device(id = 1, serialNumber = "123456", phoneNumber = "123-456-7890",uuid = UUID.randomUUID(), model = "Model X")
        val device2 = Device(id = 2, serialNumber = "789012", phoneNumber = "123-456-7891",uuid = UUID.randomUUID(), model = "Model Y")

        val deviceList = listOf(device1, device2)

        `when`(deviceRepository.findAll()).thenReturn(deviceList)

        val result = deviceService.getAllDevices()

        assertEquals(deviceList, result)
    }

    @Test
    fun testGetDeviceByIdExistingDevice() {
        val deviceId = 1L
        val device = Device(id = deviceId, serialNumber = "123456", phoneNumber = "123-456-7890",uuid = UUID.randomUUID(), model = "Model X")

        `when`(deviceRepository.findById(deviceId)).thenReturn(java.util.Optional.of(device))

        val result = deviceService.getDeviceById(deviceId)

        assertEquals(device, result)
    }

    @Test
    fun testGetDeviceByIdNonExistingDevice() {
        val deviceId = 1L

        `when`(deviceRepository.findById(deviceId)).thenReturn(java.util.Optional.empty())

        val result = deviceService.getDeviceById(deviceId)

        assertNull(result)
    }
}
