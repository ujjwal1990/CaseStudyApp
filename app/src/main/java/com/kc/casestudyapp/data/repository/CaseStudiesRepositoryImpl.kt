package com.kc.casestudyapp.data.repository

import com.kc.casestudyapp.data.local.casestudy.CaseStudyDTOConverter
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesEntity
import com.kc.casestudyapp.data.remote.api.CaseStudiesApi
import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.kc.casestudyapp.data.utils.Resource
import com.kc.casestudyapp.domain.repository.CaseStudiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//this repository will either return the all full CaseStudies data from DB/Api or return the error message
class CaseStudiesRepositoryImpl @Inject constructor(
    private val caseStudiesApi: CaseStudiesApi,
    private val caseStudiesDAO: CaseStudiesDAO
) : CaseStudiesRepository {
    override suspend fun getCaseStudies(swipeRefresh: Boolean): Flow<Resource<CaseStudiesDTO>> {
        return flow {
            try {
                if (!swipeRefresh) emit(Resource.Loading(true))
                val dbResponse = caseStudiesDAO.getAllCaseStudies()
                val shouldCallApi = swipeRefresh || dbResponse == null
                val response = if (shouldCallApi) {
                    //making the api call
                    caseStudiesApi.getCaseStudies()
                } else {
                    //getting the data from db
                    emit(Resource.Success(CaseStudyDTOConverter().stringToCaseStudyDTO(dbResponse?.caseStudyData)))
                    return@flow
                }
                if (response.isSuccessful) {
                    //saving the data in db
                    caseStudiesDAO.insertCaseStudy(
                        CaseStudiesEntity(
                            studiesKey = response.raw().request.url.toString(),
                            caseStudyData = CaseStudyDTOConverter().caseStudyDTOToString(response.body())
                        )
                    )
                    emit(Resource.Success(response.body()))
                } else {
                    response.errorBody()?.let { msg ->
                        emit(Resource.Error(msg.toString()))
                    } ?: emit(Resource.Error("Unknown error"))
                }
            } catch (e: NullPointerException) {
                emit(Resource.Error("Error in Response"))
            } catch (e: HttpException) {
                emit(Resource.Error("Oops! Something went wrong. Please try again."))
            } catch (e: IOException) {
                emit(Resource.Error("Oops! Couldn't reach server. Check your internet connection."))
            }
        }
    }
}