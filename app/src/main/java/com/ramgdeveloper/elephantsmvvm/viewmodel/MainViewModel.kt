package com.ramgdeveloper.elephantsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramgdeveloper.elephantsmvvm.model.Elephant
import com.ramgdeveloper.elephantsmvvm.network.repository.ElephantsRepository
import com.ramgdeveloper.elephantsmvvm.ui.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val elephantsRepository: ElephantsRepository): ViewModel() {

    private val _elephantResult = MutableLiveData<Resource<Elephant>>()
    val elephantResult: LiveData<Resource<Elephant>> = _elephantResult

    fun getAnElephant(){
        viewModelScope.launch {
            _elephantResult.value = Resource.Loading()
            _elephantResult.value = elephantsRepository.getAnElephant()
        }
    }
}