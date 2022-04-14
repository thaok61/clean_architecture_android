package com.me.architecture_study.data.source.local

import com.me.architecture_study.model.Location

data class LocationLocal(
    val street: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val timezone: String?
) {
    fun toLocation(): Location {
        return Location(street, city, state, country, timezone)
    }
}