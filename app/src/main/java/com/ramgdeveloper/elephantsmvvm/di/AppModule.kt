package com.ramgdeveloper.elephantsmvvm.di

import com.ramgdeveloper.elephantsmvvm.data.remote.ElephantsApi
import com.ramgdeveloper.elephantsmvvm.data.repository.ElephantsRepository
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
    fun provideElephantsApi(): ElephantsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ElephantsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideElephantsRepository(elephantsApi: ElephantsApi): ElephantsRepository{
        return ElephantsRepository(elephantsApi)
    }
}