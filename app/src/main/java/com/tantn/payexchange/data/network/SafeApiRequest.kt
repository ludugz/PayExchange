package com.tantn.payexchange.data.network

import com.tantn.payexchange.utilities.ServerException
import retrofit2.Response

interface SafeApiRequest {
    suspend fun <T> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            throw ServerException(error.toString())
        }
    }
}