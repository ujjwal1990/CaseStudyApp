package com.kc.casestudyapp.domain.usecases

import com.kc.casestudyapp.data.mappers.toCaseStudyUiModel
import com.kc.casestudyapp.data.utils.Resource
import com.kc.casestudyapp.domain.model.CaseStudiesUiModel
import com.kc.casestudyapp.domain.repository.CaseStudiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//this use-case is to get the List of CaseStudies with hero-images & teaser or error
class GetCaseStudiesListUseCase @Inject constructor(private val caseStudiesRepository: CaseStudiesRepository) {
    operator fun invoke(swipeRefresh: Boolean): Flow<Resource<List<CaseStudiesUiModel>>> = flow {
        caseStudiesRepository.getCaseStudies(swipeRefresh).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    emit(Resource.Loading(result.isLoading))
                }
                is Resource.Success -> {
                    //mapping the CaseStudiesDTO (result.data) to UiModel
                    emit(Resource.Success(result.data?.toCaseStudyUiModel()))
                }
                is Resource.Error -> {
                    emit(Resource.Error(result.errorMessage!!))
                }
            }
        }
    }
}