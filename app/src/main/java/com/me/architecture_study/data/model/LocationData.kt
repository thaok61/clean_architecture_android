package com.me.architecture_study.data.model

import com.me.architecture_study.model.Location
import com.squareup.moshi.Json

data class LocationData(
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
}