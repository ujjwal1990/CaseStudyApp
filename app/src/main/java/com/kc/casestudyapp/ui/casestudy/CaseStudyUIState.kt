package com.kc.casestudyapp.ui.casestudy

import com.kc.casestudyapp.domain.model.CaseStudiesUiModel

sealed class CaseStudyUIState {
    data class Loading(var isLoading: Boolean) : CaseStudyUIState()
    data class Success(var data: List<CaseStudiesUiModel>?) : CaseStudyUIState()
    data class Error(var errorMessage: String?) : CaseStudyUIState()
}