package com.example.myapplicationlife360.IntroductionPage.LoginProcess

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

class SignInWithEmail : AppCompatActivity() {

    lateinit var phoneNoSignIn: Button
    lateinit var continueBtn: Button
    lateinit var email: EditText
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_with_email)

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
            .allowMainThreadQueries().build()



        phoneNoSignIn = findViewById(R.id.phoneNoSignIn)
        continueBtn = findViewById(R.id.ContinueBtn)
        email = findViewById(R.id.emailId2)

        email.addTextChangedListener(emailTextWatcher)


        phoneNoSignIn.setOnClickListener() {

            intent = Intent(this, SignInWithNumber::class.java)
            startActivity(intent)
        }


    }

    private val emailTextWatcher = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (validEmail(p0.toString())) {
                continueBtn.isEnabled = true
                continueBtn.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn.setOnClickListener() {

                    loginUser()

                }

            } else {
                continueBtn.isEnabled = false
                continueBtn.backgroundTintList =
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

    fun loginUser() {
        val emailStr = email.text.toString()
        val userEmail = database.userDao().loginUserEmail(emailStr)

        if (userEmail != null) {
            intent = Intent(applicationContext, EnterPassword_Both::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "email is right", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "email is wrong", Toast.LENGTH_LONG).show()
        }
    }
}