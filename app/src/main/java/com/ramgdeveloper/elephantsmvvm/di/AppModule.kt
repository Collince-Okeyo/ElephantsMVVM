package com.ramgdeveloper.elephantsmvvm.di

import com.ramgdeveloper.elephantsmvvm.network.api.ApiService
import com.ramgdeveloper.elephantsmvvm.network.repository.ElephantsRepository
import com.ramgdeveloper.elephantsmvvm.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideElephantsRepository(apiService: ApiService): ElephantsRepository{
        return ElephantsRepository(apiService)
    }
}