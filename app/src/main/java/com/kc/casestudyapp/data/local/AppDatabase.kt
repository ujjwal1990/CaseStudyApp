package com.kc.casestudyapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kc.casestudyapp.common.Constants.Companion.DATABASE_NAME
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesEntity

@Database(entities = [CaseStudiesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }

    abstract fun caseStudiesDAO(): CaseStudiesDAO
}