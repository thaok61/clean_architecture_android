package com.me.architecture_study.di

import android.content.Context
import androidx.room.Room
import com.me.architecture_study.data.source.UserPagingRepository
import com.me.architecture_study.data.source.UsersRepository
import com.me.architecture_study.data.source.local.UserDatabase
import com.me.architecture_study.service.UserApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApplicationModule {
    private const val BASE_URL = "https://dummyapi.io/data/v1/"

//    @Qualifier
//    @Retention(RUNTIME)
//    annotation class UsersRemoteDataSource
//
//    @Qualifier
//    @Retention(RUNTIME)
//    annotation class UsersLocalDataSource

//    @UsersRemoteDataSource
//    @Provides
//    fun provideUsersRemoteDataSource(
//        userApiService: UserApiService,
//        ioDispatcher: CoroutineDispatcher
//    ): UserDataSource {
//        return UserRemoteDataSource(userApiService, ioDispatcher)
//    }

//    @UsersLocalDataSource
//    @Provides
//    fun provideUsersLocalDataSource(
//        userDatabase: UserDatabase,
//        ioDispatcher: CoroutineDispatcher
//    ): UserDataSource {
//        return UsersLocalDataSource(userDatabase.usersDao(), ioDispatcher)
//    }

    @Singleton
    @Provides
    fun provideDataBase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java, "Users.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).baseUrl(
                BASE_URL
            ).build()
    }

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}

@Module
abstract class ActivityModuleBuilder {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: UserPagingRepository): UsersRepository
}