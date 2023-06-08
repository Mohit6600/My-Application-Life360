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

class RegisterEmail : AppCompatActivity() {

    lateinit var continueBtn5: Button
    lateinit var email: EditText
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_email)



        database=Room.databaseBuilder(applicationContext,AppDatabase::class.java,"userDB").allowMainThreadQueries().build()

        email=findViewById(R.id.emailId)
        continueBtn5=findViewById(R.id.continueBtn5)


        email.addTextChangedListener(emailTextWatcher)



    }


    private val emailTextWatcher = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (validEmail(p0.toString())) {
                continueBtn5.isEnabled = true
                continueBtn5.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn5.setOnClickListener(){
                    intent= Intent(applicationContext,RegisterPassword::class.java)

                    saveData()

                    startActivity(intent)
                }

            } else {
                continueBtn5.isEnabled = false
                continueBtn5.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.purple_200))
                /* continueBtn2.setBackgroundColor(
                     ContextCompat.getColor(
                         this@RegisterMobileNo,
                         R.color.purple_200
                     )
                 )*/
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })

    fun validEmail(fullEmail: String): Boolean {

        val pattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"

        return fullEmail.matches(pattern.toRegex())
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("share_db", Context.MODE_PRIVATE)
        val userEmail: String = email.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString("userEmail", userEmail)
        editor.apply()
        Toast.makeText(this, "data saved",Toast.LENGTH_LONG).show()

    }
}