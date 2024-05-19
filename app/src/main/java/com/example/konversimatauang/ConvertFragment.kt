package com.example.konversimatauang

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class ConvertFragment : Fragment() {

    var baseCurrency = "USD"
    var convertedToCurrency = "IDR"
    var conversionRate = 0f

    private lateinit var editFromCurrency: EditText
    private lateinit var editToCurrency: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_convert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editFromCurrency = view.findViewById(R.id.editFromCurrency)
        editToCurrency = view.findViewById(R.id.editToCurrency)
        spinnerSetup(view)
        textChangedStuff()
    }

    private fun textChangedStuff() {
        editFromCurrency.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isNotEmpty()) {
                    getApiResult()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Main", "Before Text Changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Main", "On Text Changed")
            }
        })
    }

    private fun getApiResult() {
        if (editFromCurrency.text.isNotEmpty() && editFromCurrency.text.isNotBlank()) {
            val api = "https://api.currencyapi.com/v3/latest?apikey=cur_live_jPaP565zswh8xCtGhHHgE5eTt9o30v0p96cu0Tfd"

            if (baseCurrency == convertedToCurrency) {
                Toast.makeText(context, "Please pick a currency to convert", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val apiResult = URL(api).readText()
                        val jsonObject = JSONObject(apiResult)
                        conversionRate = jsonObject.getJSONObject("data").getJSONObject(convertedToCurrency).getString("value").toFloat()

                        Log.d("Main", "$conversionRate")
                        Log.d("Main", apiResult)

                        withContext(Dispatchers.Main) {
                            val text = ((editFromCurrency.text.toString().toFloat()) * conversionRate).toString()
                            editToCurrency.setText(text)
                        }
                    } catch (e: Exception) {
                        Log.e("Main", "ISSUE: $e")
                    }
                }
            }
        }
    }

    private fun spinnerSetup(view: View) {
        val firstSpinner: Spinner = view.findViewById(R.id.spFromCurrency)
        val secondSpinner: Spinner = view.findViewById(R.id.spToCurrency)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.firstCurrency_code,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            firstSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.secondCurrency_code,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            secondSpinner.adapter = adapter
        }

        firstSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                baseCurrency = parent?.getItemAtPosition(position).toString()
                getApiResult()
            }
        }

        secondSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertedToCurrency = parent?.getItemAtPosition(position).toString()
                getApiResult()
            }
        }
    }
}
