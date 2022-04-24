package com.kc.casestudyapp.data.remote.dtos.casestudies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionsItemDTO(
    @Json(name = "body_elements")
    val bodyElements: List<Any?>? = null,

    @Json(name = "title")
    val title: Any? = null
)
