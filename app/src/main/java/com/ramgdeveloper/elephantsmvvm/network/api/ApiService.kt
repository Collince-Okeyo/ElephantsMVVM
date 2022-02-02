package com.ramgdeveloper.elephantsmvvm.network.api

import com.ramgdeveloper.elephantsmvvm.model.Elephant
import com.ramgdeveloper.elephantsmvvm.util.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun getElephant(): Elephant
}