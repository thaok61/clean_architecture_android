package com.me.architecture_study.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Query("SELECT * FROM USERS")
    suspend fun getUsers(): List<UserLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userLocal: UserLocal)
}