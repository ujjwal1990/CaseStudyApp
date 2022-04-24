package com.kc.casestudyapp.domain.repository

import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.kc.casestudyapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CaseStudiesRepository {
    suspend fun getCaseStudies(swipeRefresh: Boolean): Flow<Resource<CaseStudiesDTO>>
}