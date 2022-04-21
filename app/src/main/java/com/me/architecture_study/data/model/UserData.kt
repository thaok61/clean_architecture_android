package com.me.architecture_study.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.me.architecture_study.model.User
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class UserData(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @Json(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String,
    @ColumnInfo(name = "first_name")
    @Json(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    @Json(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "gender")
    @Json(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "email")
    @Json(name = "email")
    val email: String?,
    @ColumnInfo(name = "date_of_birth")
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @ColumnInfo(name = "register_date")
    @Json(name = "registerDate")
    val registerDate: String?,
    @ColumnInfo(name = "phone")
    @Json(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "picture")
    @Json(name = "picture")
    val picture: String,
    @Embedded
    @Json(name = "location")
    val location: LocationData?
) {
    fun toUser(): User {
        return User(
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
    }
}