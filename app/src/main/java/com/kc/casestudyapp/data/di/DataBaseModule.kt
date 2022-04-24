package com.kc.casestudyapp.data.di

import android.content.Context
import com.kc.casestudyapp.data.local.AppDatabase
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCaseStudiesDAO(appDatabase: AppDatabase): CaseStudiesDAO {
        return appDatabase.caseStudiesDAO()
    }
}