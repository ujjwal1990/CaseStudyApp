package com.kc.casestudyapp.ui.casestudy

import androidx.recyclerview.widget.RecyclerView
import com.kc.casestudyapp.databinding.ItemCaseStudyListLayoutBinding
import com.kc.casestudyapp.domain.model.CaseStudiesUiModel
import com.kc.casestudyapp.ui.utils.loadImageUsingGlide

class CaseStudyListItemsViewHolder(
    private val viewBinding: ItemCaseStudyListLayoutBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(caseStudyUiModelObj: CaseStudiesUiModel) {
        with(viewBinding) {
            caseStudyHeroImage.loadImageUsingGlide(caseStudyUiModelObj.heroImage)
            caseStudyTeaserText.text = caseStudyUiModelObj.teaser
        }
    }
}