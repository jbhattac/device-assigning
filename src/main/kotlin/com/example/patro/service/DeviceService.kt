package com.example.patro.service

import com.example.patro.repo.DeviceRepository
import com.example.patro.model.Device
import org.springframework.stereotype.Service

@Service
class DeviceService(private val deviceRepository: DeviceRepository) {
    fun createDevice(device: Device): Device {
        return deviceRepository.save(device)
    }

    fun getAllDevices(): List<Device> {
        return deviceRepository.findAll()
    }

    fun getDeviceById(deviceId: Long): Device? {
        return deviceRepository.findById(deviceId).orElseGet { null }
    }
}