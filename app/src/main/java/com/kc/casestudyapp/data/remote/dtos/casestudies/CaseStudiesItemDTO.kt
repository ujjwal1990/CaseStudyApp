package com.kc.casestudyapp.data.remote.dtos.casestudies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaseStudiesItemDTO(
    @Json(name = "hero_image")
    val heroImage: String? = null,

    @Json(name = "is_enterprise")
    val isEnterprise: Boolean? = null,

    @Json(name = "client")
    val client: String? = null,

    @Json(name = "vertical")
    val vertical: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "sections")
    val sections: List<SectionsItemDTO?>? = null,

    @Json(name = "teaser")
    val teaser: String? = null,

    @Json(name = "app_store_url")
    val appStoreUrl: String? = null
)