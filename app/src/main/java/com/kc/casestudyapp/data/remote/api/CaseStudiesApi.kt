package com.kc.casestudyapp.data.remote.api

import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import retrofit2.Response
import retrofit2.http.GET

interface CaseStudiesApi {
    @GET("caseStudies.json")
    suspend fun getCaseStudies(): Response<CaseStudiesDTO>
}