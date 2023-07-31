package com.example.patro.model

import com.example.patro.model.Device
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "app_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthday: LocalDate,
    @OneToOne
    var device: Device? = null
){
    // Default constructor (required by JPA)
    constructor() : this(null, "", "", "", LocalDate.MIN, null)
}