package com.kc.casestudyapp.ui.casestudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kc.casestudyapp.data.utils.Resource
import com.kc.casestudyapp.domain.usecases.GetCaseStudiesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaseStudiesViewModel @Inject constructor(private val caseStudiesUseCase: GetCaseStudiesListUseCase) :
    ViewModel() {
    private val _caseStudyStateState = MutableLiveData<CaseStudyUIState>()
    val caseStudyState: LiveData<CaseStudyUIState> = _caseStudyStateState

    fun refresh() {
        getCaseStudies(true)
    }

    fun getCaseStudies(swipeRefresh: Boolean) {
        viewModelScope.launch {
            caseStudiesUseCase(swipeRefresh).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _caseStudyStateState.value = CaseStudyUIState.Loading(result.isLoading)
                    }
                    is Resource.Success -> {
                        _caseStudyStateState.value = CaseStudyUIState.Success(result.data)
                    }
                    is Resource.Error -> {
                        _caseStudyStateState.value =
                            CaseStudyUIState.Error(result.errorMessage)
                    }
                }
            }

        }
    }
}