package com.kc.casestudyapp.domain.di

import com.kc.casestudyapp.domain.repository.CaseStudiesRepository
import com.kc.casestudyapp.domain.usecases.GetCaseStudiesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun getCaseStudiesUseCase(caseStudiesRepository: CaseStudiesRepository): GetCaseStudiesListUseCase {
        return GetCaseStudiesListUseCase(caseStudiesRepository)
    }
}