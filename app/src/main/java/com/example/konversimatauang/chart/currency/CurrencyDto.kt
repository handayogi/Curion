package com.example.konversimatauang.chart.currency

data class CurrencyDto(
    val base_code: String,
    val conversion_rates: ConversionRates,
    val documentation: String,
    val result: String,
    val terms_of_use: String,
    val time_last_update_unix: Int,
    val time_last_update_utc: String,
    val time_next_update_unix: Int,
    val time_next_update_utc: String
)