package com.eadbox.eduardo.teste.view.activity.splash

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eadbox.eduardo.teste.R
import com.eadbox.eduardo.teste.util.Constants
import com.eadbox.eduardo.teste.view.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupHandler()
    }

    private fun setupHandler() {
        val handler = Handler()
        handler.postDelayed({goToNextScreen()}, Constants.Layout.SPLASH_SCREEN_TIME_MILLIS)
    }

    private fun goToNextScreen() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

}
