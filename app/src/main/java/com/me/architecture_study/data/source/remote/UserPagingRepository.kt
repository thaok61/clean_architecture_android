package com.me.architecture_study.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.me.architecture_study.data.source.local.UserDatabase
import com.me.architecture_study.model.User
import com.me.architecture_study.service.UserApiService
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 20

class UserPagingRepository(
    private val service: UserApiService,
    private val database: UserDatabase
) {
    fun getUsersPaging(): Flow<PagingData<UserRemote>> {
        val pagingSourceFactory = { database.usersDao().getPagingUsers() }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = UserRemoteMediator(service, database)
        )
    }
}