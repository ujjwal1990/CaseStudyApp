package com.kc.casestudyapp.ui.casestudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc.casestudyapp.databinding.ItemCaseStudyListLayoutBinding
import com.kc.casestudyapp.domain.model.CaseStudiesUiModel

class CaseStudyListAdapter(private val caseStudyData: List<CaseStudiesUiModel>) :
    RecyclerView.Adapter<CaseStudyListItemsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CaseStudyListItemsViewHolder {
        return CaseStudyListItemsViewHolder(
            ItemCaseStudyListLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CaseStudyListItemsViewHolder, position: Int) {
        holder.bind(caseStudyUiModelObj = caseStudyData[position])
    }

    override fun getItemCount(): Int {
        return caseStudyData.size
    }
}