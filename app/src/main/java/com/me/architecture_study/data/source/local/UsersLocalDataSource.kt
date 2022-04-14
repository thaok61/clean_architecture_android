package com.me.architecture_study.data.source.local

import com.me.architecture_study.data.source.Result
import com.me.architecture_study.data.source.UserDataSource
import com.me.architecture_study.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UsersLocalDataSource internal constructor(
    private val usersDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {
    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            val users = usersDao.getUsers().map {
                it.toUser()
            }
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun addUser(user: User) = withContext(ioDispatcher) {
        usersDao.insertUser(user.toUserLocal())
    }
}