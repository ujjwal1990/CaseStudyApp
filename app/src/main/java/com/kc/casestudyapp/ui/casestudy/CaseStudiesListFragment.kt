package com.kc.casestudyapp.ui.casestudy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kc.casestudyapp.R
import com.kc.casestudyapp.databinding.FragmentCaseStudyListBinding
import com.kc.casestudyapp.domain.model.CaseStudiesUiModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CaseStudiesListFragment : Fragment(R.layout.fragment_case_study_list) {
    private var _binding: FragmentCaseStudyListBinding? = null
    private val binding get() = _binding!!

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val caseStudiesViewModel: CaseStudiesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCaseStudyListBinding.bind(view)
        observeCaseStudiesViewModel()
        setUpSwipeView()
        caseStudiesViewModel.getCaseStudies(false)
    }

    private fun observeCaseStudiesViewModel() {
        caseStudiesViewModel.caseStudyState.observe(viewLifecycleOwner) {
            when (it) {
                is CaseStudyUIState.Loading -> {
                    binding.loaderView.visibility = View.VISIBLE
                }
                is CaseStudyUIState.Success -> {
                    stopLoading()
                    showDataInList(it.data)
                }
                is CaseStudyUIState.Error -> {
                    stopLoading()
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showDataInList(listData: List<CaseStudiesUiModel>?) {
        with(binding.caseStudyListView) {
            layoutManager = LinearLayoutManager(requireContext())
            listData?.let { adapter = CaseStudyListAdapter(it) }
        }
    }

    private fun setUpSwipeView() {
        binding.swipeView.setOnRefreshListener {
            caseStudiesViewModel.refresh()
        }
        binding.swipeView.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    private fun stopLoading() {
        binding.loaderView.visibility = View.GONE
        binding.swipeView.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}