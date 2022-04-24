package com.kc.casestudyapp.data.local.casestudy

import androidx.room.*

@Dao
interface CaseStudiesDAO {
    @Query("SELECT * FROM $CASE_STUDIES_TABLE_NAME")
    suspend fun getAllCaseStudies(): CaseStudiesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaseStudy(vararg caseStudyData: CaseStudiesEntity)

    @Query("DELETE FROM $CASE_STUDIES_TABLE_NAME")
    suspend fun deleteAllCaseStudies()
}