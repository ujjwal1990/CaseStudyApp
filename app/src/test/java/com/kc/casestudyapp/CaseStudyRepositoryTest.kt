package com.kc.casestudyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kc.casestudyapp.CaseStudyTestData.CASE_STUDIES_DATA
import com.kc.casestudyapp.common.Constants.Companion.EMPTY
import com.kc.casestudyapp.data.local.AppDatabase
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesEntity
import com.kc.casestudyapp.data.remote.api.CaseStudiesApi
import com.kc.casestudyapp.data.repository.CaseStudiesRepositoryImpl
import com.kc.casestudyapp.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
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


@ExperimentalCoroutinesApi
class CaseStudyRepositoryTest {
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

    @Mock
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testRepoReturnLoading() {
        runTest {
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            repoImpl.getCaseStudies(true).collect { result ->
                if (result is Resource.Loading) {
                    assertNull(result.errorMessage)
                    assertNull(result.data)
                }
            }
        }
    }

    @Test
    fun testRepoReturnSuccess() {
        val caseStudyDTO = CaseStudyTestData.getCaseStudyResponse()
        runTest {
            whenever(db.caseStudiesDAO()).thenReturn(dao)
            whenever(mockCaseStudiesApi.getCaseStudies()).thenReturn(caseStudyDTO)
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            repoImpl.getCaseStudies(true).collect { result ->
                assertEquals(caseStudyDTO.body()?.caseStudies, result.data?.caseStudies)
            }
        }
    }

    @Test
    fun testRepoReturnNullResponse() {
        runTest {
            whenever(mockCaseStudiesApi.getCaseStudies()).thenReturn(null)
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            repoImpl.getCaseStudies(true).collect { result ->
                assertTrue(result is Resource.Error)
                assertEquals(result.errorMessage, "Error in Response")
            }
        }
    }

    @Test
    fun testRepoReturnDBResponse() {
        runTest {
            whenever(dao.getAllCaseStudies()).thenReturn(
                CaseStudiesEntity(
                    EMPTY,
                    CASE_STUDIES_DATA
                )
            )
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            repoImpl.getCaseStudies(false).collect { result ->
                if (result is Resource.Success) {
                    assertEquals(
                        result.data?.caseStudies!![0]?.teaser,
                        "Testing Tube brakes, with TfL Decelerator"
                    )
                }
            }
        }
    }

    @Test
    fun testRepoWhenNoDataInDB() {
        runTest {
            whenever(dao.getAllCaseStudies()).thenReturn(null)
            val repoImpl = CaseStudiesRepositoryImpl(mockCaseStudiesApi, dao)
            repoImpl.getCaseStudies(false).collect { result ->
                if (result is Resource.Error) {
                    assertEquals(result.errorMessage, "Error in Response")
                }
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        db.close()
    }
}