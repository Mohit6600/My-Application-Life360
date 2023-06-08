package com.example.myapplicationlife360.IntroductionPage.InvitationCode

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationlife360.IntroductionPage.MapsActivity
import com.example.myapplicationlife360.R

class GiveInvitationCode :AppCompatActivity() {

    lateinit var backBtn2:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_invitation_code)

        backBtn2=findViewById(R.id.backBtn2)



        backBtn()
    }

    private fun backBtn() {

        backBtn2.setOnClickListener() {

            intent = Intent(applicationContext, MapsActivity::class.java)
            startActivity(intent)

        }

    }



}