package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrimate.R
import com.example.nutrimate.databinding.ActivityLoginBinding
import com.example.nutrimate.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener{

        }
        binding.tvRegisterLogin.setOnClickListener{
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}