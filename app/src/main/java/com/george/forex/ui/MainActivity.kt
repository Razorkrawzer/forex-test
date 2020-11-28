package com.george.forex.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.*
import com.george.forex.DatePickerFragment
import com.george.forex.databinding.ActivityMainBinding
import com.george.forex.utils.CustomDataEntry


class MainActivity : AppCompatActivity(), MainActivity_MVP.view {

    private lateinit var binding : ActivityMainBinding
    var presenter : MainActivity_Presenter = MainActivity_Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateBtn.setOnClickListener(View.OnClickListener {
            binding.mainProgressBar.visibility = View.VISIBLE
            presenter.setDate(calendarDate(), context())
        })

        binding.editDate.setOnClickListener(View.OnClickListener {
            showDatePickerDialog()
        })

    }

    override fun context(): Context {
        return applicationContext
    }

    override fun calendarDate(): String {
       return binding.editDate.text.toString()
    }

    override fun emptyDate(message: Int) {
        binding.mainProgressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showGraph(usdCurrency: Double, eurCurrency: Double, date: String) {
        binding.mainProgressBar.visibility = View.GONE

        val cartesian : Cartesian = AnyChart.column()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("USD", usdCurrency))
        data.add(ValueDataEntry("EUR", eurCurrency))
        cartesian.data(data)

        cartesian.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0)
            .offsetY(5)
            .format("\${%Value}{groupsSeparator: }")

        cartesian.animation(true)
        cartesian.yScale().minimum(0)
        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Currencies");
        cartesian.yAxis(0).title("Rates");

        binding.chart.setChart(cartesian)


    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val dayStr = day.twoDigits()
            val monthStr = (month + 1).twoDigits()
            val selectDate = "$year-$monthStr-$dayStr"
            binding.editDate.setText(selectDate)
        })
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun Int.twoDigits() =
        if (this <= 9) "0$this" else this.toString()
}