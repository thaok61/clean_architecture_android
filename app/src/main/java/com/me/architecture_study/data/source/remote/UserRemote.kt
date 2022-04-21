package com.me.architecture_study.data.source.remote

import com.me.architecture_study.data.source.local.UserLocal
import com.me.architecture_study.model.User
import com.squareup.moshi.Json

data class UserRemote(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @Json(name = "registerDate")
    val registerDate: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "location")
    val location: LocationRemote?
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
        location?.toLocation()
    )

    fun toLocal() = UserLocal(
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
        location?.toLocal()
    )
}