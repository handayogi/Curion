package com.example.konversimatauang.chart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.konversimatauang.R
import com.example.konversimatauang.databinding.ActivityChart2Binding
import com.example.konversimatauang.databinding.ActivityChart3Binding
import com.example.konversimatauang.databinding.ActivityChartBinding

class Chart3Activity : AppCompatActivity() {

    private var _binding: ActivityChart3Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChart3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            chart3.gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )
            chart3.animation.duration = animationDuration
            chart3.animate(lineSet)
            chart3.onDataPointTouchListener= {index, _, _ ->

            }
        }
    }

    companion object {
        private val lineSet = listOf(
            "" to 6f,
            "" to 4.5f,
            "" to 7.7f,
            "" to 3.5f,
            "" to 5.6f,
            "" to 7.5f,
            "" to 5.9f,
            "" to 5.8f,
            "" to 5.45f,
            "" to 5.6f,
            "" to 5.76f,
            "" to 7.5f
        )

        private const val animationDuration = 1000L
    }
}