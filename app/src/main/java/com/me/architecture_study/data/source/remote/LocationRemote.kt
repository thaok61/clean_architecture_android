package com.me.architecture_study.data.source.remote

import com.me.architecture_study.data.source.local.LocationLocal
import com.me.architecture_study.model.Location
import com.squareup.moshi.Json

data class LocationRemote(
    @Json(name = "street")
   val street: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "timezone")
    val timezone: String
) {
    fun toLocation() = Location(street, city, state, country, timezone)
    fun toLocal() = LocationLocal(street, city, state, country, timezone)
}