package com.sevdotdev.statshotrebound.data.di

import com.sevdotdev.statshotrebound.data.network.AlternateHostInterceptor
import com.sevdotdev.statshotrebound.data.network.MatchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesHttpClient(hostInterceptor: AlternateHostInterceptor): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(hostInterceptor)
        .build()

    @Provides
    fun provideRetroFit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(STATSHOT_API_BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun providesMatchService(retrofit: Retrofit): MatchService = retrofit.create(MatchService::class.java)
}

const val STATSHOT_API_BASE_URL = "http://stat-shot-rebound.herokuapp.com/"