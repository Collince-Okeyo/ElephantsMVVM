package com.ramgdeveloper.elephantsmvvm.data.remote

import com.ramgdeveloper.elephantsmvvm.model.Elephants
import com.ramgdeveloper.elephantsmvvm.util.Constants.END_POINT
import retrofit2.http.GET

interface ElephantsApi {
    @GET(END_POINT)
    suspend fun getElephant(): List<Elephants>
}