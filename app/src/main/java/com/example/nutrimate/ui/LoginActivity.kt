package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nutrimate.data.Result
import com.example.nutrimate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = obtainViewModel(this@LoginActivity)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener{
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()
            Log.d("LoginActivity",email)
            Log.d("LoginActivity",password)
            if(email.isNotEmpty() and password.isNotEmpty()){
                loginResult("go")
//                loginViewModel.login(email, password).observe(this){
//                    if(it!=null){
//                        if(it is Result.Success){
//                            loginResult(it.data.token)
//                        } else if(it is Result.Error){
//                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
//                        } else if(it is Result.Loading){
//
//                        }
//                    }
//                }
            }
        }

        binding.tvLoginRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginResult(data: String) {
        if(data == ""){

        } else {
            Log.d("LoginResult","$data")
            Toast.makeText(this, "Login Success",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra(MainActivity.EXTRA_TOKEN,data)
            startActivity(intent)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
    }
}