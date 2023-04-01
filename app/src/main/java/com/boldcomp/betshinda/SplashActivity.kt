package com.boldcomp.betshinda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT =3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val progress_bar = findViewById<ProgressBar>(R.id.progress_bar)

        Handler(Looper.getMainLooper()).postDelayed({

            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
            progress_bar.visibility = View.GONE
        }, SPLASH_TIME_OUT)
    }
}