package com.example.myapplicationlife360.IntroductionPage.RegistrationProcess

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.AppDatabase

import com.example.myapplicationlife360.R


class RegisterFullName : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var continueBtn4: Button
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_fullname)


        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
            .allowMainThreadQueries().build()

        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        continueBtn4 = findViewById(R.id.continueBtn4)


        firstname.addTextChangedListener(nameTextWatcher)
        lastname.addTextChangedListener(nameTextWatcher)


    }


    private val nameTextWatcher = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            if (isValidName(p0.toString())) {
                continueBtn4.isEnabled = true
                continueBtn4.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn4.setOnClickListener() {

                    intent = Intent(applicationContext, RegisterEmail::class.java)

                    saveData()

                    startActivity(intent)
                }

            } else {
                continueBtn4.isEnabled = false
                continueBtn4.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.purple_200))
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })


    fun isValidName(fullName: String): Boolean {
        val user = firstname.text.isNotEmpty() && lastname.text.isNotEmpty()
        return user
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("share_db", Context.MODE_PRIVATE)
        val userFirstName: String = firstname.text.toString()
        val userLastName:String=lastname.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("userFirstName", userFirstName)
        editor.putString("userLastName",userLastName)
        editor.apply()
        Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show()

    }

}