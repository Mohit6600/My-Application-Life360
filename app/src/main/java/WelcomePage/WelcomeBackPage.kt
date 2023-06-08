package com.example.myapplicationlife360.IntroductionPage.WelcomePage

import Dashboard.DashboardActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationlife360.IntroductionPage.GetStartedActivity
import com.example.myapplicationlife360.R

class WelcomeBackPage : AppCompatActivity() {

    lateinit var forwardBtn: Button
    lateinit var signOut: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back_page)


        forwardBtn = findViewById(R.id.forwardBtn)
        signOut = findViewById(R.id.signOut)

        forwardBtn.setOnClickListener() {

            intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        signOut.setOnClickListener() {

            intent = Intent(applicationContext, GetStartedActivity::class.java)
            startActivity(intent)
        }


    }


}