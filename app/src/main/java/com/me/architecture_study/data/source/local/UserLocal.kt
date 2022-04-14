package com.me.architecture_study.data.source.local

import androidx.room.*
import com.me.architecture_study.model.User

@Entity(tableName = "users")
data class UserLocal(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String?,
    @ColumnInfo(name = "register_date")
    val registerDate: String?,
    @ColumnInfo(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "picture")
    val picture: String,
    @Embedded
    val location: LocationLocal?
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