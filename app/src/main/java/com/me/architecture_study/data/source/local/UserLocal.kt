package com.me.architecture_study.data.source.local

import com.me.architecture_study.data.source.remote.LocationRemote

data class UserLocal(
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
    val location: LocationLocal
)