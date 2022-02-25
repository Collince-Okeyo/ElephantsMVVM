package com.ramgdeveloper.elephantsmvvm.data.repository

import com.ramgdeveloper.elephantsmvvm.data.remote.ElephantsApi
import com.ramgdeveloper.elephantsmvvm.model.Elephants
import java.lang.Exception
import javax.inject.Inject

class ElephantsRepository @Inject constructor(private val elephantsApi: ElephantsApi) {

    suspend fun getElephant() : com.ramgdeveloper.elephantsmvvm.util.Resource<List<Elephants>> {
        val response = try {
            elephantsApi.getElephant()
        }catch (e: Exception){
            return com.ramgdeveloper.elephantsmvvm.util.Resource.Error("Unknown error occurred")
        }
        return com.ramgdeveloper.elephantsmvvm.util.Resource.Success(response)
    }
}