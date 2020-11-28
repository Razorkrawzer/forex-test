package com.george.forex.ui

import android.content.Context
import com.george.forex.R
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity_Presenter(view: MainActivity_MVP.view) : MainActivity_MVP.presenter {

    var vist: MainActivity_MVP.view = view
    var interactor: MainActivity_MVP.interactor = MainActivity_Interactor(this)


    override fun setView(view: MainActivity_MVP.view) {
        vist = view
    }

    override fun setDate(date: String, context: Context) {
        if (vist != null) {
            if (vist.calendarDate().isEmpty()) {

                vist.emptyDate(R.string.empty_DateField)

            } else {

                interactor.getDate(vist.calendarDate(), vist.context())

            }

        }
    }

    override fun ratesData(usdCurrency: Double, eurCurrency: Double, date: String) {
        if (vist != null){
            vist.showGraph(usdCurrency, eurCurrency, date)
        }
    }
}