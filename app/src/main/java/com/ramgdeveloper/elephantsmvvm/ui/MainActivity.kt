package com.ramgdeveloper.elephantsmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ramgdeveloper.elephantsmvvm.R
import com.ramgdeveloper.elephantsmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchedElephantsObserver()
        viewModel.getAnElephant()

    }

    private fun fetchedElephantsObserver(){
        viewModel.elephantResult.observe(this, Observer {
            when(it) {
                is Resource.Success -> {
                    //Log.d(TAG, "fetchedElephantsObserver: ${it.data?.get(2)}")
                    Timber.d("Success: ${it.data?.get(0)}")
                }
                is Resource.Loading -> {
                    Timber.d("Loading: Fetching Data")
                }
                is Resource.Failure -> {
                    Timber.d("Failure: ${it.message}")
                }
            }
        })
    }
}