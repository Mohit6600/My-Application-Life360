package com.example.myapplicationlife360.IntroductionPage.OptionMenu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationlife360.IntroductionPage.MapsActivity
import com.example.myapplicationlife360.R

class Message : AppCompatActivity() {

    lateinit var backBtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)


        backBtn=findViewById(R.id.backBtn)


        backBtn()

    }

    private fun backBtn(){

        backBtn.setOnClickListener(){

            intent=Intent(applicationContext,MapsActivity::class.java)
            startActivity(intent)

        }

    }


}