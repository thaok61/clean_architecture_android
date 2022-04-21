package com.me.architecture_study.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysLocal>)

    @Query("SELECT * FROM remote_keys WHERE userId = :userId")
    suspend fun remoteKeysRepoId(userId: String): RemoteKeysLocal?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}