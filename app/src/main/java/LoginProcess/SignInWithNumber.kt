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
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.AppDatabase
import com.example.myapplicationlife360.R
import com.hbb20.CountryCodePicker

class SignInWithNumber : AppCompatActivity() {

    lateinit var countryCodePicker: CountryCodePicker
    lateinit var signInWithEmail: Button
    lateinit var textMobileNo2: EditText
    lateinit var continueBtn3: Button
    lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_with_number)


        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
            .allowMainThreadQueries().build()


        countryCodePicker = findViewById(R.id.countryCodePicker)

        countryCodePicker.setOnCountryChangeListener {

            val countryName = countryCodePicker.selectedCountryName
            val countryCode = countryCodePicker.selectedCountryCode
            val countryCodeName = countryCodePicker.selectedCountryNameCode
            val countryCodeWithPlus = countryCodePicker.selectedCountryCodeWithPlus
            val countryFlagImage = countryCodePicker.selectedCountryFlagResourceId
        }


        signInWithEmail = findViewById(R.id.emailSignIn)

        signInWithEmail.setOnClickListener() {
            intent = Intent(this, SignInWithEmail::class.java)
            startActivity(intent)


        }

        textMobileNo2 = findViewById(R.id.textMobileNo2)
        continueBtn3 = findViewById(R.id.ContinueBtn3)
        signInWithEmail = findViewById(R.id.emailSignIn)

        textMobileNo2.addTextChangedListener(loginTextWatcher2)

        signInWithEmail.setOnClickListener() {

            intent = Intent(this, EnterPassword_Both::class.java)
            startActivity(intent)

        }

    }

    private val loginTextWatcher2 = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            if (isValidMobileNumber(p0.toString())) {
                continueBtn3.isEnabled = true
                continueBtn3.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn3.setOnClickListener() {

                    loginUser()

                }
            } else {
                continueBtn3.isEnabled = false
                continueBtn3.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.purple_200))
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })


    private fun isValidMobileNumber(mobileNumber: String): Boolean {
        val pattern = "^[+]?[0-9]{10,13}\$"
        return mobileNumber.matches(pattern.toRegex())
    }

    fun loginUser() {
        val phoneStr = textMobileNo2.text.toString()
        val userPhoneNo = database.userDao().loginUserPhNumber(phoneStr)

        if (userPhoneNo != null) {
            intent = Intent(applicationContext, EnterPassword_Both::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "phoneNo is right", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "PhoneNO is wrong", Toast.LENGTH_LONG).show()
        }
    }

}