package com.tantn.payexchange.data.network

import com.tantn.payexchange.data.model.ExchangeResult
import com.tantn.payexchange.utilities.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("api/live")
    suspend fun getSearchResultData(
        @Query(value = "access_key") accessKey: String,
        @Query(value = "currencies") currency: String
    ): Response<ExchangeResult>

    companion object {
        fun createInstance(): ApiInterface {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}