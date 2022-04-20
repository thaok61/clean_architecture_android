package com.me.architecture_study.data.source

import com.me.architecture_study.model.User

interface UsersRepository {
    suspend fun getUsers(forceUpdate: Boolean): Result<List<User>>
    suspend fun loadMoreFromRemote(page: Int = 0): Result<List<User>>
}