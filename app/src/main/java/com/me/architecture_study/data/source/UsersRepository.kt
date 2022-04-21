package com.me.architecture_study.data.source

import androidx.paging.PagingData
import com.me.architecture_study.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<PagingData<User>>
}