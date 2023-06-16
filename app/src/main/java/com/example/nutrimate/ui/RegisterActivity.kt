package com.example.nutrimate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nutrimate.data.Result
import com.example.nutrimate.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
//    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = obtainViewModel(this)
//        auth = Firebase.auth
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener{
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            if(email.isNotEmpty() and password.isNotEmpty()){
//                createAccount(email,password)
//                registerViewModel.register(email, password).observe(this){
//                    if(it!=null){
//                        if(it is Result.Success){
//                            Toast.makeText(this, "Account successfully registered", Toast.LENGTH_SHORT).show()
//                        } else if(it is Result.Error){
//                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
//                        } else if(it is Result.Loading){
//
//                        }
//                    }
//                }
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

//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("RegisterActivity", "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    Toast.makeText(baseContext,"Registration Success",Toast.LENGTH_SHORT).show()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w("RegisterActivity", "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//
//                }
//            }
//        // [END create_user_with_email]
//    }
}