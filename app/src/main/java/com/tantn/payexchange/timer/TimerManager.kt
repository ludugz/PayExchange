package com.tantn.payexchange.timer

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import com.tantn.payexchange.ui.HomeViewModel

/**
 * Use to refresh data every a specific amount of time
 */
class TimerManager(val context: Context, val viewModel: HomeViewModel) {
    private var countDownTimer: CountDownTimer
    private lateinit var selectedCountry: String
    private var selectedAmount: Double = 0.0

    companion object {
        const val LIMIT_DURATION = 30 * 60 * 1000L // 30 mins
        const val INTERVAL = 1000L
    }

    init {
        countDownTimer = object : CountDownTimer(LIMIT_DURATION, INTERVAL) {
            override fun onFinish() {
                try {
                    viewModel.getExchangeRate(selectedCountry, selectedAmount)
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        context.applicationContext,
                        "Can not get the amount!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                restartTimer()
            }

            override fun onTick(millisUntilFinished: Long) {
            }
        }
    }

    fun setDataTimer(selectedCountry: String, selectedAmount: Double) {
        this.selectedCountry = selectedCountry
        this.selectedAmount = selectedAmount
        restartTimer()
    }

    private fun restartTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }
}