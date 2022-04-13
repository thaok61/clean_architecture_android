package com.me.architecture_study.data.source.remote

import com.me.architecture_study.model.User

data class UserRemote(
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
    val location: LocationRemote
) {
    fun toUser() = User(
        id,
        title,
        firstName,
        lastName,
        gender,
        email,
        dateOfBirth,
        registerDate,
        phone,
        picture,
        location.toLocation()
    )
}