package com.me.architecture_study.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysLocal(
    @PrimaryKey
    val userId: String,
    val prevKey: Int?,
    val nextKey: Int?
)