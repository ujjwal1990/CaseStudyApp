package com.kc.casestudyapp.data.local.casestudy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CASE_STUDIES_TABLE_NAME = "case_studies"
@Entity(tableName = CASE_STUDIES_TABLE_NAME)
data class CaseStudiesEntity(
    @PrimaryKey
    @ColumnInfo(name = "studies_key") val studiesKey: String,
    @ColumnInfo(name = "case_study_data") val caseStudyData: String
)