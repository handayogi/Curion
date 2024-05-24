package com.example.konversimatauang.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import com.example.konversimatauang.R
import com.example.konversimatauang.databinding.ActivityChartBinding
import com.github.mikephil.charting.data.Entry

class ChartActivity : AppCompatActivity() {

    private var _binding: ActivityChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            chart.gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )
            chart.animation.duration = animationDuration
            chart.animate(lineSet)
            chart.onDataPointTouchListener= {index, _, _ ->

            }
        }
    }

    companion object {
        private val lineSet = listOf(
            "" to 5.994f,
            "" to 5.994f,
            "" to 5.994f,
            "" to 5.992f,
            "" to 5.996f,
            "" to 6.2f,
            "" to 7.5f,
            "" to 5.995f,
            "" to 6f,
            "" to 6.5f,
            "" to 5.992f,
            "" to 5.992f
        )

        private const val animationDuration = 1000L
    }

}