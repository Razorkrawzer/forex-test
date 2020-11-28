package com.george.forex.ui

import android.content.Context
import android.util.Log
import com.george.forex.api.RetrofitClient
import com.george.forex.models.RatesHistory
import com.george.forex.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_Repository {


    private val TAG = MainActivity_Repository::class.simpleName
    var retrofitClient = RetrofitClient()
    val apiKey = Constants.API_TOKEN
    val currency : String = "USD,EUR"


    fun getHistoricRates(date: String, context: Context, res: MainActivity_MVP.responnse){

        retrofitClient.provideUser().historicalRates(date, apiKey, currency).enqueue(object : Callback<RatesHistory>{
            override fun onResponse(call: Call<RatesHistory>, response: Response<RatesHistory>) {
                if (response.isSuccessful){
                    val body = response.body()
                    val selectedDate : String = body!!.date
                    val usd : Double = body?.rates!!.USD
                    val eur : Double = body.rates.EUR

                    res.rateResponse(true, usd, eur, selectedDate)

                }
            }

            override fun onFailure(call: Call<RatesHistory>, t: Throwable) {

                res.rateResponse(false, 0.0, 0.0, "")
            }

        })


    }
}