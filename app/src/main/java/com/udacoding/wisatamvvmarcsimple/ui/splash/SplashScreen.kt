package com.udacoding.wisatamvvmarcsimple.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.udacoding.wisatamvvmarcsimple.R
import com.udacoding.wisatamvvmarcsimple.MainActivity

import android.content.Intent
import android.os.Handler
import com.udacoding.wisatamvvmarcsimple.ui.login.Login


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashScreen, Login::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}