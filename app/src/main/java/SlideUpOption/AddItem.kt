package com.example.myapplicationlife360.IntroductionPage.SlideUpOption

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationlife360.R

class AddItem :AppCompatActivity() {

lateinit var textView18:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)


        // it is used for the working of hyperLinkBtn
         textView18 = findViewById(R.id.textView18)
        textView18.movementMethod = LinkMovementMethod.getInstance()


    }
}