package com.me.architecture_study.data.source

import com.me.architecture_study.model.User

interface UserDataSource {
    suspend fun getUsers(): Result<List<User>>
    suspend fun addUser(user: User)
    suspend fun addAllUser(users: List<User>)
    suspend fun deleteAllUsers()
    suspend fun deleteUser(userId: String): Int
}