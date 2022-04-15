package com.me.architecture_study.data.source

import android.util.Log
import com.me.architecture_study.di.ApplicationModule
import com.me.architecture_study.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject

private val TAG = DefaultUserRepository::class.java.simpleName

class DefaultUserRepository @Inject constructor(
    @ApplicationModule.UsersRemoteDataSource private val userRemoteDataSource: UserDataSource,
    @ApplicationModule.UsersLocalDataSource private val userLocalDataSource: UserDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {
    private var cachedUsers: ConcurrentMap<String, User>? = null
    override suspend fun getUsers(forceUpdate: Boolean): Result<List<User>> {
        return withContext(ioDispatcher) {
            if (!forceUpdate) {
                Log.d(TAG, "getUsers: Return Success Local Data")
                cachedUsers?.let { cachedUsers ->
                    return@withContext Result.Success(cachedUsers.values.toList())
                }
            }

            val newUsers = fetchUsersFromRemoteOrLocal(forceUpdate)

            (newUsers as? Result.Success)?.let { refreshCache(it.data) }
            Log.d(TAG, "getUsers: Return Success Remote Data")

            cachedUsers?.values?.let { users ->
                return@withContext Result.Success(users.toList())
            }

            Log.d(TAG, "getUsers: Return Error")

            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    private fun refreshCache(data: List<User>) {
        cachedUsers?.clear()
        data.forEach {
            cacheAndPerform(it) {}
        }
    }

    private fun cacheAndPerform(user: User, perform: (User) -> Unit) {
        val cachedUser = cacheUser(user)
        perform(user)
    }

    private fun cacheUser(user: User): User {
        if (cachedUsers == null) {
            cachedUsers = ConcurrentHashMap()
        }
        cachedUsers?.put(user.id, user)
        return user
    }

    private suspend fun fetchUsersFromRemoteOrLocal(forceUpdate: Boolean): Result<List<User>> {
        when (val remoteUsers = userRemoteDataSource.getUsers()) {
            is Result.Error -> Log.d(TAG, "fetchUsersFromRemoteOrLocal: ")
            is Result.Success -> {
                refreshLocalDataSource(remoteUsers.data)
                return remoteUsers
            }
            else -> throw IllegalStateException()
        }

        if (forceUpdate) {
            return Result.Error(Exception("Refresh failed"))
        }

        val localUsers = userLocalDataSource.getUsers()
        if (localUsers is Result.Success) return localUsers
        return Result.Error(Exception("Error fetching from remote and local"))
    }

    private suspend fun refreshLocalDataSource(data: List<User>) {
        userLocalDataSource.deleteAllUsers()
        userLocalDataSource.addAllUser(data)
    }

}