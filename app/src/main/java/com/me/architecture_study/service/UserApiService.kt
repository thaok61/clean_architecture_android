package com.me.architecture_study.service

import com.me.architecture_study.data.source.remote.UserRemote
import com.me.architecture_study.data.source.remote.UserRemoteResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private const val APP_ID = "625675b297240a4c3006f9f3"


interface UserApiService {
    @Headers(
        "app-id: $APP_ID"
    )
    @GET("user")
    suspend fun getUser(@Query("page") page: Int = 0): UserRemoteResponse
}