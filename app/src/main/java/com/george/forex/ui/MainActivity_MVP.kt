package com.george.forex.ui

import android.content.Context

interface MainActivity_MVP {

    interface view{
        fun context() : Context
        fun calendarDate() : String

        fun emptyDate(message : Int)
        fun showGraph(usdCurrency: Double, eurCurrency: Double, date: String)
    }

    interface presenter{
        fun setView(view : MainActivity_MVP.view)
        fun setDate(date : String, context: Context)

        fun ratesData(usdCurrency: Double, eurCurrency: Double, date: String)
    }

    interface interactor{
        fun getDate(date: String, context: Context)
    }

    interface responnse{
        fun rateResponse(response : Boolean, usdCurrency: Double, eurCurrency: Double, date: String)
    }


}