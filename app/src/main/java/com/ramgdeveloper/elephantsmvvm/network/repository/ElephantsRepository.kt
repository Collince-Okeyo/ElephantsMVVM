package com.ramgdeveloper.elephantsmvvm.network.repository

import com.ramgdeveloper.elephantsmvvm.network.api.ApiService
import com.ramgdeveloper.elephantsmvvm.network.api.SafeApiCall
import javax.inject.Inject

class ElephantsRepository @Inject constructor(private val apiService: ApiService): SafeApiCall() {

    suspend fun getAnElephant() = safeApiCall {
        apiService.getElephant()
    }
}