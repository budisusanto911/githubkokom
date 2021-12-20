package com.udacoding.wisatamvvmarcsimple.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.udacoding.wisatamvvmarcsimple.databinding.ActivityLoginBinding
import com.udacoding.wisatamvvmarcsimple.ui.forgot.ForgotPasswordActivity
import com.udacoding.wisatamvvmarcsimple.ui.home.Home

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.run {
            forgot.setOnClickListener {
                val intent = Intent(this@Login, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            btnLogin.setOnClickListener {
                val intent = Intent(this@Login, Home::class.java)
                startActivity(intent)
            }
        }

    }
}