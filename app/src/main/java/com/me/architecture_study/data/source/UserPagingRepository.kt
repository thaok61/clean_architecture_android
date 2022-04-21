package com.me.architecture_study.data.source

import androidx.paging.*
import com.me.architecture_study.data.source.local.UserDatabase
import com.me.architecture_study.data.source.remote.UserRemoteMediator
import com.me.architecture_study.model.User
import com.me.architecture_study.service.UserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 20

class UserPagingRepository @Inject constructor(
    private val service: UserApiService,
    private val database: UserDatabase,
    private val mediator: UserRemoteMediator
) : UsersRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<User>> {
        val pagingSourceFactory = { database.usersDao().getPagingUsers() }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = mediator
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUser()
            }
        }
    }
}