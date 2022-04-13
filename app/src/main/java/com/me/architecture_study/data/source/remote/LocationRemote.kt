package com.me.architecture_study.data.source.remote

import com.me.architecture_study.model.Location

data class LocationRemote(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String
) {
    fun toLocation() = Location(street, city, state, country, timezone)
}