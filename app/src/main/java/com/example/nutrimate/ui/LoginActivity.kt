package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrimate.R
import com.example.nutrimate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener{
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }
        binding.tvLoginRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}