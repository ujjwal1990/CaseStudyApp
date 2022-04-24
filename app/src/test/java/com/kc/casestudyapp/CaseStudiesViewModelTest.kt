package com.kc.casestudyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kc.casestudyapp.common.Constants
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesEntity
import com.kc.casestudyapp.data.remote.api.CaseStudiesApi
import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.kc.casestudyapp.data.repository.CaseStudiesRepositoryImpl
import com.kc.casestudyapp.domain.usecases.GetCaseStudiesListUseCase
import com.kc.casestudyapp.ui.casestudy.CaseStudiesViewModel
import com.kc.casestudyapp.ui.casestudy.CaseStudyUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class CaseStudiesViewModelTest {
    @Rule
    @JvmField
    var mockRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var mockCaseStudiesApi: CaseStudiesApi

    @Mock
    private lateinit var dao: CaseStudiesDAO

    private lateinit var studyViewModel: CaseStudiesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testViewModelSendingErrorToView() {
        runBlocking {
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            studyViewModel = CaseStudiesViewModel(GetCaseStudiesListUseCase(repoImpl))
            studyViewModel.getCaseStudies(false)
            studyViewModel.caseStudyState.observeForever {
                assertTrue(it is CaseStudyUIState.Error)
            }
        }
    }

    @Test
    fun testViewModelSendingCaseStudyDataToViewByApiCall() {
        val caseStudyDTO: Response<CaseStudiesDTO> = CaseStudyTestData.getCaseStudyResponse()
        runBlocking {
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            whenever(mockCaseStudiesApi.getCaseStudies()).thenReturn(caseStudyDTO)
            studyViewModel = CaseStudiesViewModel(GetCaseStudiesListUseCase(repoImpl))
            studyViewModel.refresh()
            studyViewModel.caseStudyState.observeForever { result ->
                assertTrue(result is CaseStudyUIState.Success)
                assertEquals(caseStudyDTO.body()?.caseStudies?.size, 2)
                if (result is CaseStudyUIState.Success) {
                    //because result is the filtered list which will avoid the entry if hero_image ot teaser empty
                    assertNotEquals(caseStudyDTO.body()?.caseStudies?.size, result.data?.size)
                    assertEquals(1, result.data?.size)
                }
            }
        }
    }

    @Test
    fun testViewModelSendingCaseStudyDataToViewFromDB() {
        runBlocking {
            whenever(dao.getAllCaseStudies()).thenReturn(
                CaseStudiesEntity(
                    Constants.EMPTY,
                    CaseStudyTestData.CASE_STUDIES_DATA
                )
            )
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            studyViewModel = CaseStudiesViewModel(GetCaseStudiesListUseCase(repoImpl))
            studyViewModel.getCaseStudies(false)
            studyViewModel.caseStudyState.observeForever { result ->
                assertTrue(result is CaseStudyUIState.Success)
                if (result is CaseStudyUIState.Success)
                    assertEquals(1, result.data?.size)
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}