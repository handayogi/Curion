package com.example.konversimatauang

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HomeFragment : Fragment() {

    private lateinit var lineChart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        lineChart = rootView.findViewById(R.id.lineChart)
        fetchCurrencyData()
        return rootView
    }

    private fun fetchCurrencyData() {
        val baseUrl = "https://v6.exchangerate-api.com/v6/424b60014bc4901886ddb931/latest/USD"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val url = URL(baseUrl)
                val connection = url.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"

                val response = StringBuilder()
                BufferedReader(InputStreamReader(connection.inputStream)).use {
                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                }

                val jsonResponse = JSONObject(response.toString())
                val rates = jsonResponse.getJSONObject("conversion_rates")

                val currencyList = mutableListOf<Entry>()
                var i = 0f

                val keys = rates.keys().asSequence().toList().sorted().take(5)
                keys.forEach { key ->
                    val rate = rates.getDouble(key).toFloat()
                    currencyList.add(Entry(i++, rate))
                }

                showChart(currencyList)

            } catch (e: Exception) {
                e.printStackTrace()
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showChart(currencyList: List<Entry>) {
        val lineDataSet = LineDataSet(currencyList, "Currency")
        lineDataSet.color = Color.BLUE
        lineDataSet.setDrawValues(false)

        val dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)

        activity?.runOnUiThread {
            lineChart.data = lineData

            val description = Description()
            description.text = "Currency Rate Chart"
            lineChart.description = description

            lineChart.invalidate()
        }
    }
}
