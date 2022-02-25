package com.ramgdeveloper.elephantsmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ramgdeveloper.elephantsmvvm.R
import com.ramgdeveloper.elephantsmvvm.adapter.ElephantsAdapter
import com.ramgdeveloper.elephantsmvvm.databinding.ActivityMainBinding
import com.ramgdeveloper.elephantsmvvm.util.Resource
import com.ramgdeveloper.elephantsmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { ElephantsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        subscribeToElephantsObserver()
    }
    private fun subscribeToElephantsObserver() {
        viewModel.elephantsResult.observe(this, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    val elephants = result.data
                    adapter.submitList(elephants)
                    binding.elephantsRecycler.adapter = adapter
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Snackbar.make(binding.root, result.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}