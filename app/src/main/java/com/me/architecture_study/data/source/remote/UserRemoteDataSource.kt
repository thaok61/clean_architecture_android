package com.me.architecture_study.data.source.remote

import com.me.architecture_study.data.source.Result
import com.me.architecture_study.data.source.UserDataSource
import com.me.architecture_study.model.User
import com.me.architecture_study.service.UserApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRemoteDataSource internal constructor(
    private val userApiService: UserApiService,
    private val ioDispatcher: CoroutineDispatcher
) : UserDataSource {
    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            val listUsers = userApiService.getUser().data.map {
                it.toUser()
            }
            Result.Success(listUsers)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun addAllUser(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String): Int {
        TODO("Not yet implemented")
    }
}