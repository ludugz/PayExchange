package com.tantn.payexchange.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantn.payexchange.R
import com.tantn.payexchange.timer.TimerManager
import com.tantn.payexchange.ui.adapter.HomeRecyclerAdapter
import com.tantn.payexchange.utilities.State
import com.tantn.payexchange.utilities.hide
import com.tantn.payexchange.utilities.show
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter
    private lateinit var selectedCountry: String
    private lateinit var timerManger: TimerManager
    private var selectedAmount: Double = 0.0
    private val codeArray: Array<String> by lazy { resources.getStringArray(R.array.country_code_array) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setUpViewModel()
        handleViewListener()
        handleResponseCallBack()
        initTimer()
    }

    private fun initTimer() {
        timerManger = TimerManager(this, viewModel)
    }

    private fun handleResponseCallBack() {
        viewModel.exchangeListLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Success -> {
                    progressBar.hide()
                    errorTextView.hide()
                    recyclerView.show()
                    homeRecyclerAdapter.setExchangeData(state.data)
                }
                is State.Loading -> {
                    progressBar.show()
                    errorTextView.hide()
                    recyclerView.hide()
                }
                is State.Failure -> {
                    progressBar.hide()
                    recyclerView.hide()
                    errorTextView.show()
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun handleViewListener() {
        codeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCountry = parent?.getItemAtPosition(position) as String
            }
        }

        getButton.setOnClickListener {
            try {
                selectedAmount = amountEditText.text.toString().toDouble()
                viewModel.getExchangeRate(selectedCountry, selectedAmount)
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Can not get the amount!", Toast.LENGTH_SHORT)
                    .show()
            }
            timerManger.setDataTimer(selectedCountry, selectedAmount)
        }
    }

    private fun setUpViewModel() {
        // init ViewModel
        viewModelFactory = HomeViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun initView() {
        // Spinner
        val adapter = ArrayAdapter(
            this.applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            codeArray
        )
        codeSpinner.adapter = adapter

        // Recycler View
        recyclerView.apply {
            homeRecyclerAdapter = HomeRecyclerAdapter()
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setAdapter(homeRecyclerAdapter)
        }
    }
}
