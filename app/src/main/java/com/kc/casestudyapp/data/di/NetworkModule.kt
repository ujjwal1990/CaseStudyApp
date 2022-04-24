package com.kc.casestudyapp.data.di

import com.kc.casestudyapp.common.Constants.Companion.BASE_URL
import com.kc.casestudyapp.data.local.casestudy.CaseStudiesDAO
import com.kc.casestudyapp.data.remote.api.CaseStudiesApi
import com.kc.casestudyapp.data.repository.CaseStudiesRepositoryImpl
import com.kc.casestudyapp.domain.repository.CaseStudiesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesRetrofit(moshi: Moshi): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient).build()
    }

    @Provides
    fun provideCaseStudiesApi(retrofit: Retrofit): CaseStudiesApi {
        return retrofit.create(CaseStudiesApi::class.java)
    }

    @Provides
    fun providesCaseStudiesRepository(
        caseStudiesApi: CaseStudiesApi,
        caseStudiesDAO: CaseStudiesDAO
    ): CaseStudiesRepository {
        return CaseStudiesRepositoryImpl(caseStudiesApi, caseStudiesDAO)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}