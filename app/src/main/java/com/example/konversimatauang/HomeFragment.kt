package com.example.konversimatauang

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )
            lineChart2.gradientFillColors = intArrayOf(
                Color.parseColor("#81FFFFFF"),
                Color.TRANSPARENT
            )

            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)

            lineChart2.animation.duration = animationDuration
            lineChart2.animate(lineSet2)
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
            "label11" to 3f,
            "label12" to 7f
        )

        private const val animationDuration = 1000L
    }

}
