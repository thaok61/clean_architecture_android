package com.me.architecture_study.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.me.architecture_study.data.model.UserData

@Dao
interface UsersDao {
    @Query("SELECT * FROM USERS")
    suspend fun getUsers(): List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(userData: List<UserData>)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String): Int

    @Query("SELECT * FROM USERS")
    fun getPagingUsers(): PagingSource<Int, UserData>
}