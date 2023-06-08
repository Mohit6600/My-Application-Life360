package com.example.myapplicationlife360.IntroductionPage.RoomDatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplicationlife360.R

class MainActivity : AppCompatActivity() {

    lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB").build()


    }
}