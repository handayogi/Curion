package com.example.konversimatauang

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser!=null) {
                if (!hasOpenedMainActivityBefore()) {
                    markMainActivityAsOpened()
                    startActivity(Intent(this, MenuActivity::class.java))
                } else {
                    goToMenu()
                }
            } else {
                goToMainActivity()
            }
            finish()
        }, 3000L)
    }

    private fun hasOpenedMainActivityBefore():Boolean {
        return sharedPreferences.getBoolean("hasOpened", false)
    }

    private fun markMainActivityAsOpened() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("hasOpenedMainActivity", true)
        editor.apply()
    }

    private fun goToMenu() {
        Intent(this, MenuActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun goToMainActivity() {
        Intent(this, OnboardingActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}