package com.example.myapplicationlife360.IntroductionPage.RegistrationProcess

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.room.Room
import com.example.myapplicationlife360.IntroductionPage.ModelClass.UserInformation
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.AppDatabase
import com.example.myapplicationlife360.R

class RegisterPassword : AppCompatActivity() {

    lateinit var password: EditText
    lateinit var visibilityBtn: ImageButton
    lateinit var continueBtn5: Button
    private var showPass = false
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)

        database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
                .allowMainThreadQueries().build()


        password = findViewById(R.id.password)
        visibilityBtn = findViewById(R.id.visibilityBtn)
        continueBtn5 = findViewById(R.id.continueBtn5)


        password.addTextChangedListener(passwordTextWatcher)
        visibilityBtn.isInvisible = password.text.isEmpty()


        visibilityBtn.setOnClickListener {
            showPass = !showPass
            showVisibilityBtn(showPass)     //it helps to work the toggle
        }


    }

    val passwordTextWatcher = (object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            // this if condition check that edit text field is empty or not then according to this eye icon or toogle visible or invisible
            if (p0.isNullOrEmpty()) {
                visibilityBtn.isInvisible =
                    true    //it show that text is empty and make button invisible
            } else {
                visibilityBtn.isInvisible =
                    false   //it show that text is not empty and make button visible
            }

            // this is used to enable the button and change the colour of text
            if (isValidPassword(p0.toString())) {

                continueBtn5.isEnabled = true
                continueBtn5.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn5.setOnClickListener() {
                    intent = Intent(applicationContext, AppIntroduction::class.java)

                    getData()

                    startActivity(intent)
                }

            } else {
                continueBtn5.isEnabled = false
                continueBtn5.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.purple_200))

            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })


    fun showVisibilityBtn(isShow: Boolean) {

        if (isShow) {
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            visibilityBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

        } else {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            visibilityBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
        password.setSelection(password.text.toString().length)

    }

    fun isValidPassword(validPassword: String): Boolean {
        val userPassword = password.length() >= 6
        return userPassword

    }

    fun getData() {
        val sharedPreferences = getSharedPreferences("share_db", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("userPhoneNo", "")
        val data1=sharedPreferences.getString("userFirstName","")
        val data2=sharedPreferences.getString("userLastName","")
        val data3=sharedPreferences.getString("userEmail","")
        val data4 = password.text.toString()
        database.userDao().addUser(UserInformation(0, "$data1", "$data2", "$data4", "$data3", "$data"))
    }

}