package com.me.architecture_study.data.model

import com.squareup.moshi.Json

data class UserRemoteResponse(
    @Json(name = "data")
    val data: List<UserData>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "limit")
    val limit: Int
)
