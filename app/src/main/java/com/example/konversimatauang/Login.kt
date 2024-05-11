package com.example.konversimatauang

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.konversimatauang.databinding.ActivityLoginBinding
import com.example.konversimatauang.databinding.ActivityRegisterBinding
import com.example.konversimatauang.Register
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val pass = binding.edtPasswordLogin.text.toString()

            if (email.isEmpty()) {
                binding.edtEmailLogin.error = "Fill the email!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailLogin.error = "Email is not valid!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                binding.edtEmailLogin.error = "Fill the password!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }
            LoginFirebase(email,pass)
        }
    }

    private fun LoginFirebase(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Welcome To Curion", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}