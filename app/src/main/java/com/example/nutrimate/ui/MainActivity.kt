package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrimate.R
import com.example.nutrimate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAdd.setOnClickListener{
            val intent = Intent(this@MainActivity,PhotoActivity::class.java)
            startActivity(intent)
        }
    }
}