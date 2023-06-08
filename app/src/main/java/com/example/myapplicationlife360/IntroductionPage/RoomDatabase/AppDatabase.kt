package com.example.myapplicationlife360.IntroductionPage.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplicationlife360.IntroductionPage.ModelClass.UserInformation


@Database(entities = [UserInformation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}