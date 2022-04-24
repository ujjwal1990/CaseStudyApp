package com.kc.casestudyapp.data.local.casestudy

import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

//converter class for DB as we can store only primitives in db
class CaseStudyDTOConverter {
    private val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    fun caseStudyDTOToString(dto: CaseStudiesDTO?): String {
        val jsonAdapter: JsonAdapter<CaseStudiesDTO> = moshi.adapter(CaseStudiesDTO::class.java)
        return jsonAdapter.toJson(dto)
    }

    fun stringToCaseStudyDTO(stringData: String?): CaseStudiesDTO? {
        val jsonAdapter = moshi.adapter(CaseStudiesDTO::class.java)
        return stringData?.let { jsonAdapter.fromJson(it) }
    }
}