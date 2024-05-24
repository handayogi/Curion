package com.example.konversimatauang.chart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.konversimatauang.R
import com.example.konversimatauang.databinding.ActivityChart4Binding
import com.example.konversimatauang.databinding.ActivityChartBinding

class Chart4Activity : AppCompatActivity() {

    private var _binding: ActivityChart4Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChart4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            chart4.gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )
            chart4.animation.duration = animationDuration
            chart4.animate(lineSet)
            chart4.onDataPointTouchListener= {index, _, _ ->

            }
        }
    }

    companion object {
        private val lineSet = listOf(
            "" to 4.8f,
            "" to 5.5f,
            "" to 6.7f,
            "" to 3.5f,
            "" to 5.6f,
            "" to 8.5f,
            "" to 7.5f,
            "" to 10f,
            "" to 5f,
            "" to 6.5f,
            "" to 6.2f,
            "" to 4f
        )

        private const val animationDuration = 1000L
    }
}