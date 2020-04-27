package com.tantn.payexchange.data.repositories

import com.tantn.payexchange.data.model.ExchangeResult
import com.tantn.payexchange.data.network.ApiInterface
import com.tantn.payexchange.data.network.SafeApiRequest
import com.tantn.payexchange.utilities.Utils

class HomeRepository : SafeApiRequest {
    private val apiInterface = ApiInterface.createInstance()
    suspend fun getExchangeResult(): ExchangeResult {
        return apiRequest {
            apiInterface.getSearchResultData(
                Utils.ACCESS_KEY,
                Utils.CURRENCIES_PARAM
            )
        }
    }
}