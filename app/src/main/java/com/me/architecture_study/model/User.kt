package com.me.architecture_study.model

import com.me.architecture_study.data.source.local.LocationLocal

data class User(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val registerDate: String,
    val phone: String,
    val picture: String,
    val location: Location
)