package com.example.myapplicationlife360.IntroductionPage.OptionMenu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationlife360.IntroductionPage.MapsActivity
import com.example.myapplicationlife360.R

class Setting : AppCompatActivity() {

    lateinit var crossSign: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        crossSign = findViewById(R.id.crossSign)

        backBtn()

    }

    private fun backBtn() {

        crossSign.setOnClickListener() {

            intent = Intent(applicationContext, MapsActivity::class.java)
            startActivity(intent)

        }

    }

}