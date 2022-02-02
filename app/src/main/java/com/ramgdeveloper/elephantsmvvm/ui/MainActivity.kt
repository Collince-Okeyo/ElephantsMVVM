package com.ramgdeveloper.elephantsmvvm.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ramgdeveloper.elephantsmvvm.R
import com.ramgdeveloper.elephantsmvvm.adapter.ElephantsAdapter
import com.ramgdeveloper.elephantsmvvm.databinding.ActivityMainBinding
import com.ramgdeveloper.elephantsmvvm.util.Resource
import com.ramgdeveloper.elephantsmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ElephantsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        fetchedElephantsObserver()
        viewModel.getAnElephant()
        adapter = ElephantsAdapter(View.OnClickListener {

        })
    }

    private fun fetchedElephantsObserver() {
        viewModel.elephantResult.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    Timber.d("Success: ${it.data?.get(0)}")
                    /*CoroutineScope(Dispatchers.IO).launch {
                        Timber.d("${adapter.submitList(it.data)}")
                        Timber.d("Data displayed in the Recyclerview")
                    }*/
                }
                is Resource.Loading -> {
                    Timber.d("Loading: Fetching Data")
                    Toast.makeText(this, "Loading data", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Timber.d("Failure: ${it.message}")
                    Toast.makeText(this, "Loading Data Failed", Toast.LENGTH_SHORT).show()
                    /*val snack = Snackbar.make(applicationContext, "Passwords do not match", Snackbar.LENGTH_LONG)
                    snack.setActionTextColor(Color.GREEN)
                    snack.show()*/
                }
            }
        })
    }
}