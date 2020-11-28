package com.george.forex.ui

import android.content.Context

class MainActivity_Interactor(mainactivityPresenter: MainActivity_Presenter) : MainActivity_MVP.interactor {

    var mainRepository :MainActivity_Repository = MainActivity_Repository()
    var presenter : MainActivity_Presenter = mainactivityPresenter


    override fun getDate(date: String, context: Context) {

        mainRepository.getHistoricRates(date, context, object : MainActivity_MVP.responnse{
            override fun rateResponse(response: Boolean, usdCurrency: Double, eurCurrency: Double, date: String) {
                if (response){
                    presenter.ratesData(usdCurrency, eurCurrency, date)
                }else{

                }
            }

        })
    }
}