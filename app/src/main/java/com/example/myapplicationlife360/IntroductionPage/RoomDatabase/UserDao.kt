package com.example.myapplicationlife360.IntroductionPage.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplicationlife360.IntroductionPage.ModelClass.UserInformation
import com.example.myapplicationlife360.IntroductionPage.RegistrationProcess.RegisterPassword

@Dao
interface UserDao {

    @Insert
     fun addUser(user: UserInformation)

    @Query("Select * From UserInformation")
    fun getUserInformation(): List<UserInformation>

    @Query("Select * From UserInformation where email = :email")
    fun loginUserEmail(email: String): UserInformation

    @Query("Select * From UserInformation where password = :password")
    fun loginUserPassword(password: String): UserInformation

    @Query("Select * From UserInformation where phone = :phone")
    fun loginUserPhNumber(phone: String): UserInformation
}