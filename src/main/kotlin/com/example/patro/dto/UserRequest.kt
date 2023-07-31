package com.example.patro.dto

import java.time.LocalDate

class UserRequest (
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthday: LocalDate
)