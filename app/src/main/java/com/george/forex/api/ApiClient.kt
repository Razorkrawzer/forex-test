package com.george.forex.api

import com.george.forex.models.RatesHistory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {


    @GET("{date}")
    fun historicalRates(@Path("date") date : String,
                        @Query("access_key") key : String,
                        @Query("symbols") currency : String) : Call<RatesHistory>
}