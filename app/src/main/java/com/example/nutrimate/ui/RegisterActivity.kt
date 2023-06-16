package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nutrimate.data.Result
import com.example.nutrimate.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = obtainViewModel(this)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener{
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            if(email.isNotEmpty() and password.isNotEmpty()){
                registerViewModel.register(email, password).observe(this){
                    if(it!=null){
                        if(it is Result.Success){
                            Toast.makeText(this, "Account successfully registered", Toast.LENGTH_SHORT).show()
                        } else if(it is Result.Error){
                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                        } else if(it is Result.Loading){

                        }
                    }
                }
            }
        }
        binding.tvRegisterLogin.setOnClickListener{
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): RegisterViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RegisterViewModel::class.java)
    }
}