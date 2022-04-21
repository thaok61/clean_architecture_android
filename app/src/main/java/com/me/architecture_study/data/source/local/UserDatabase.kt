package com.me.architecture_study.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocal::class, RemoteKeysLocal::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun remotesKeyDao(): RemoteKeysDao
}