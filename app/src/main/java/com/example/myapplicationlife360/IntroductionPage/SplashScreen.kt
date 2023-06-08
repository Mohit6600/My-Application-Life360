package com.example.myapplicationlife360.IntroductionPage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myapplicationlife360.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {

            val intent = Intent(this, GetStartedActivity::class.java)
            startActivity(intent)
            finish()    // it used to when we don't want to come splash screen

        }, 2000)

        supportActionBar?.hide()

    }

}



