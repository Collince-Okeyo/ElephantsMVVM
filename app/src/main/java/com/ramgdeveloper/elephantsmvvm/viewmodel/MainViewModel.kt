package com.ramgdeveloper.elephantsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramgdeveloper.elephantsmvvm.model.Elephants
import com.ramgdeveloper.elephantsmvvm.data.repository.ElephantsRepository
import com.ramgdeveloper.elephantsmvvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val elephantsRepository: ElephantsRepository): ViewModel() {

    private val _elephantResult = MutableLiveData<Resource<List<Elephants>>>()
    val elephantsResult: LiveData<Resource<List<Elephants>>> = _elephantResult

    init {
        getElephant()
    }

    private fun getElephant() {
        _elephantResult.value = Resource.Loading()
        viewModelScope.launch {
            _elephantResult.value = elephantsRepository.getElephant()
        }
    }
}