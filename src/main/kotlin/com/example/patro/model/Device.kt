

package com.example.patro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Builder
import java.util.*

@Entity
@Builder
data class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val serialNumber: String,
    val uuid: UUID,
    val phoneNumber: String,
    val model: String
){
    // Default constructor (required by JPA)
    constructor() : this(null, "", UUID.randomUUID(), "", "")
}