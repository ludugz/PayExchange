package com.tantn.payexchange.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tantn.payexchange.R
import com.tantn.payexchange.data.model.ExchangeResult
import com.tantn.payexchange.data.repositories.HomeRepository
import com.tantn.payexchange.utilities.ServerException
import com.tantn.payexchange.utilities.State
import com.tantn.payexchange.utilities.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private lateinit var exchangeRateResult: ExchangeResult
    val exchangeListLiveData = MutableLiveData<State<ArrayList<Pair<String, Double>?>>>()
    private var usdBaseExchangeRate =
        mutableMapOf<String, Double>() // A USE base currency rate map (f.e <United Arab Emirates Dirham, 3.67315>)
    private val countryNameMap =
        mutableMapOf<String, String>() // A map for converting country code to country name (f.e AED --> United Arab Emirates Dirham )
    private val codeArray: Array<String> by lazy { context.resources.getStringArray(R.array.country_code_array) }
    private val countryArray: Array<String> by lazy { context.resources.getStringArray(R.array.country_full_name_array) }

    init {
        codeArray.forEachIndexed { index, code ->
            countryNameMap[code] = countryArray[index]
        }
    }

    fun getExchangeRate(code: String, amount: Double) {
        exchangeListLiveData.postValue(State.loading())
        // Call the API to get data
        viewModelScope.launch {
            try {
                exchangeRateResult = HomeRepository().getExchangeResult()
                withContext(Dispatchers.Main) {
                    if (exchangeRateResult.success) {
                        // In case response success
                        countryArray.forEachIndexed { index, countryName ->
                            usdBaseExchangeRate[countryName] =
                                Utils.getRateList(exchangeRateResult.quotes)[index]
                        }
                        val countryName = countryNameMap[code]
                        val exchangeRateList = usdBaseExchangeRate.mapValues {
                            it.value * amount / (usdBaseExchangeRate[countryName]!!)
                        }.toList() as ArrayList<Pair<String, Double>?>
                        exchangeListLiveData.postValue(State.success(exchangeRateList))
                    } else {
                        // In case response error
                        exchangeListLiveData.postValue(State.failure("Cannot get response from API!"))
                    }
                }
            } catch (e: ServerException) {
                exchangeListLiveData.postValue(State.failure("Server Error!"))
            } catch (e: Exception) {
                exchangeListLiveData.postValue(State.failure(e.message!!))
            }
        }
    }
}