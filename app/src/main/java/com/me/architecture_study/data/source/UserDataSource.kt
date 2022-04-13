package com.me.architecture_study.data.source

import com.me.architecture_study.model.User

interface UserDataSource {
    suspend fun getUsers():Result<List<User>>
}