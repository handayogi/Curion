package com.example.konversimatauang

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicators(0)

        auth = FirebaseAuth.getInstance()
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.item1,
                    title = "Konversi Cepat & Akurat",
                    description = "Dapatkan nilai tukar mata uang terkini dalam hitungan detik, kapan saja dan di mana saja. Tingkatkan efisiensi keuangan Anda."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.item2,
                    title = "Grafik Pergerakan Mata Uang",
                    description = "Analisis tren mata uang dengan grafik interaktif. Buat keputusan investasi yang lebih baik dengan data yang akurat."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.item3,
                    title = "Siap untuk Memulai?",
                    description = "Daftarkan diri Anda sekarang dan nikmati semua fitur yang kami tawarkan. Konversi mata uang tidak pernah semudah ini!"
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })

        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.nextImage).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigateToLogin()
            }
        }

        if (onboardingViewPager.currentItem == onboardingItemsAdapter.itemCount) {
            if (auth.currentUser!=null) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                navigateToLogin()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            if (auth.currentUser!=null) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                navigateToLogin()
            }
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            if (auth.currentUser!=null) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(applicationContext, Login::class.java))
        finish()
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicators(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}