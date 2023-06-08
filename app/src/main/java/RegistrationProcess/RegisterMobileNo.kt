package com.example.myapplicationlife360.IntroductionPage.RegistrationProcess

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.myapplicationlife360.IntroductionPage.ModelClass.UserInformation
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.AppDatabase
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.UserDao
import com.example.myapplicationlife360.R
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterMobileNo : AppCompatActivity() {

    lateinit var countryCodePicker: CountryCodePicker
    lateinit var textMobileNo: EditText
    lateinit var continueBtn2: Button
    lateinit var database: AppDatabase//
    lateinit var user: TextView      //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_mobile_no)



        database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
                .allowMainThreadQueries().build()

        countryCodePicker = findViewById(R.id.countryCodePicker1)

        // it is used to get country code and flag
        countryCodePicker.setOnCountryChangeListener {

            val countryName = countryCodePicker.selectedCountryName
            val countryCode = countryCodePicker.selectedCountryCode
            val countryCodeName = countryCodePicker.selectedCountryNameCode
            val countryCodeWithPlus = countryCodePicker.selectedCountryCodeWithPlus
            val countryFlagImage = countryCodePicker.selectedCountryFlagResourceId


        }

        //  it is used for the working of hyperLinkBtn
        val textView: TextView = findViewById(R.id.textView)
        textView.movementMethod = LinkMovementMethod.getInstance()

        textMobileNo = findViewById(R.id.textMobileNo)
        continueBtn2 = findViewById(R.id.ContinueBtn2)
        user = findViewById(R.id.text20)          //

        textMobileNo.addTextChangedListener(loginTextWatcher)


    }

    private val loginTextWatcher = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            if (isValidMobileNumber(p0.toString())) {
                continueBtn2.isEnabled = true
                continueBtn2.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn2.setOnClickListener() {

                    intent = Intent(applicationContext, RegisterFullName::class.java)


                    saveData()      //


                    startActivity(intent)

                }

            } else {
                continueBtn2.isEnabled = false
                continueBtn2.backgroundTintList =
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

    fun isValidMobileNumber(mobileNumber: String): Boolean {
        val pattern = "^[+]?[0-9]{10,13}\$"
        return mobileNumber.matches(pattern.toRegex())
    }

    //
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("share_db", Context.MODE_PRIVATE)
        val phoneNo: String = textMobileNo.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("userPhoneNo", phoneNo)
        editor.apply()
        Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show()

        val userno = sharedPreferences.getString("userPhoneNo", "")
        user.text = "username is : $phoneNo"
    }


}