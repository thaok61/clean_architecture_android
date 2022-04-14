package com.me.architecture_study.data.source.remote

import com.squareup.moshi.Json

data class UserRemoteResponse(
    @Json(name = "data")
    val data: List<UserRemote>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "limit")
    val limit: Int
)
