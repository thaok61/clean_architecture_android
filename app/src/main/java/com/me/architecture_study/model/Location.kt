package com.me.architecture_study.model

import com.me.architecture_study.data.source.local.LocationLocal

data class Location(
    val street: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val timezone: String?
) {
    fun toLocationLocal(): LocationLocal {
        return LocationLocal(street, city, state, country, timezone)
    }
}