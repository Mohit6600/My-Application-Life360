package com.example.myapplicationlife360.IntroductionPage.ModelClass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserInformation")

data class UserInformation(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val Firstname: String,
    val lastName:String,
    val password: String,
    val email: String,
    val phone:String

)