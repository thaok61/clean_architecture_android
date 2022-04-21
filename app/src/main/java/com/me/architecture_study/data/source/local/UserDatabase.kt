package com.me.architecture_study.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.me.architecture_study.data.model.RemoteKeysLocal
import com.me.architecture_study.data.model.UserData

@Database(entities = [UserData::class, RemoteKeysLocal::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun remotesKeyDao(): RemoteKeysDao
}