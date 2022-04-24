package com.kc.casestudyapp.data.mappers

import com.kc.casestudyapp.common.Constants.Companion.EMPTY
import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.kc.casestudyapp.domain.model.CaseStudiesUiModel

fun CaseStudiesDTO.toCaseStudyUiModel(): List<CaseStudiesUiModel> {
    val modelUiList = caseStudies?.map {
        CaseStudiesUiModel(teaser = it?.teaser ?: EMPTY, heroImage = it?.heroImage ?: EMPTY)
    } ?: emptyList()
    return modelUiList.filter { it.teaser.isNotEmpty() && it.heroImage.isNotEmpty() }
}