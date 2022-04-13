package com.me.architecture_study.service

import com.me.architecture_study.data.source.remote.UserRemote
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dummyapi.io/data/v1/"

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(
            ScalarsConverterFactory.create()
        ).baseUrl(
            BASE_URL
        ).build()

interface UserApiService {
    @GET("user")
    suspend fun getUser(): List<UserRemote>
}