package com.example.konversimatauang

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.konversimatauang.chart.ChartActivity
import com.example.konversimatauang.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cardView.setOnClickListener {
                startActivity(Intent(requireContext(), ChartActivity::class.java))
            }
            cardView2.setOnClickListener {
                startActivity(Intent(requireContext(), ChartActivity::class.java))
            }
            cardView3.setOnClickListener {
                startActivity(Intent(requireContext(), ChartActivity::class.java))
            }
            cardView4.setOnClickListener {
                startActivity(Intent(requireContext(), ChartActivity::class.java))
            }

            val gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )

            lineChart.gradientFillColors = gradientFillColors
            lineChart2.gradientFillColors = gradientFillColors
            lineChart3.gradientFillColors = gradientFillColors
            lineChart4.gradientFillColors = gradientFillColors

            lineChart.animation.duration = animationDuration
            lineChart2.animation.duration = animationDuration
            lineChart3.animation.duration = animationDuration
            lineChart4.animation.duration = animationDuration

            lineChart.animate(lineSet)
            lineChart2.animate(lineSet2)
            lineChart3.animate(lineSet3)
            lineChart4.animate(lineSet4)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val lineSet = listOf(
            "label1" to 5.994f,
            "label2" to 5.994f,
            "label3" to 5.994f,
            "label4" to 5.992f,
            "label5" to 5.996f,
            "label6" to 6.2f,
            "label7" to 7.5f,
            "label8" to 5.995f,
            "label9" to 6f,
            "label10" to 6.5f,
            "label11" to 5.992f,
            "label12" to 5.992f
        )

        private val lineSet2 = listOf(
            "label1" to 6f,
            "label2" to 4.5f,
            "label3" to 7.7f,
            "label4" to 3.5f,
            "label5" to 5.6f,
            "label6" to 7.5f,
            "label7" to 7.5f,
            "label8" to 10f,
            "label9" to 5f,
            "label10" to 6.5f,
            "label11" to 6.2f,
            "label12" to 5.7f
        )

        private val lineSet3 = listOf(
            "label1" to 6f,
            "label2" to 4.5f,
            "label3" to 7.7f,
            "label4" to 3.5f,
            "label5" to 5.6f,
            "label6" to 7.5f,
            "label7" to 5.9f,
            "label8" to 5.8f,
            "label9" to 5.45f,
            "label10" to 5.6f,
            "label11" to 5.76f,
            "label12" to 7.5f
        )

        private val lineSet4 = listOf(
            "label1" to 4.8f,
            "label2" to 5.5f,
            "label3" to 6.7f,
            "label4" to 3.5f,
            "label5" to 5.6f,
            "label6" to 8.5f,
            "label7" to 7.5f,
            "label8" to 10f,
            "label9" to 5f,
            "label10" to 6.5f,
            "label11" to 6.2f,
            "label12" to 4f
        )

        private const val animationDuration = 1000L
    }

}
