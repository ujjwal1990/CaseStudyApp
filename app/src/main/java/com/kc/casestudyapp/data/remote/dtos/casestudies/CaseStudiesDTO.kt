package com.kc.casestudyapp.data.remote.dtos.casestudies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaseStudiesDTO(
    @Json(name = "case_studies")
    val caseStudies: List<CaseStudiesItemDTO?>? = null
)
