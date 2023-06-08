package com.example.myapplicationlife360.IntroductionPage.LoginProcess

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.myapplicationlife360.IntroductionPage.RoomDatabase.AppDatabase
import com.example.myapplicationlife360.IntroductionPage.WelcomePage.WelcomeBackPage
import com.example.myapplicationlife360.R

class EnterPassword_Both : AppCompatActivity() {
    lateinit var password2: EditText
    lateinit var visibilityBtn: ImageButton
    lateinit var continueBtn8: Button
    private var showPass = false
    lateinit var database: AppDatabase

    lateinit var toast: Toast
    lateinit var view: View
    lateinit var customToast:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_password_both)


        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userDB")
            .allowMainThreadQueries().build()

        password2 = findViewById(R.id.password2)
        visibilityBtn = findViewById(R.id.visibilityBtn)
        continueBtn8 = findViewById(R.id.continueBtn8)





        continueBtn8.isEnabled = true
        continueBtn8.setOnClickListener() {
            if (password2.text.isEmpty()) {
                password2.error = "enter password"
                return@setOnClickListener
            }
        }

        password2.addTextChangedListener(passwordTextWatcher)
        visibilityBtn.isInvisible = password2.text.isEmpty()



        visibilityBtn.setOnClickListener() {
            showPass = !showPass
            showVisibilityBtn(showPass)
        }


    }

    private val passwordTextWatcher = (object : TextWatcher {
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

                continueBtn8.isEnabled = true
                continueBtn8.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.yellow))
                continueBtn8.setOnClickListener() {

                    loginUserPassword()

                }

            } else {
                continueBtn8.isEnabled = true
                continueBtn8.setOnClickListener() {
                    if (password2.text.toString().length < 6) {
                        password2.error = "password contain at least 6 digit"
                        return@setOnClickListener
                    }
                }
                continueBtn8.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.purple_200))


            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })

    private fun showVisibilityBtn(isShow: Boolean) {

        if (isShow) {
            password2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            visibilityBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

        } else {
            password2.transformationMethod = PasswordTransformationMethod.getInstance()
            visibilityBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
        password2.setSelection(password2.text.toString().length)

    }


    fun isValidPassword(validPassword: String): Boolean {
        val userPassword = password2.length() >= 6
        return userPassword

    }

    fun loginUserPassword() {

        val passwordStr = password2.text.toString()
        val userPass = database.userDao().loginUserPassword(passwordStr)

        if (userPass != null) {
            intent = Intent(applicationContext, WelcomeBackPage::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "password is right", Toast.LENGTH_LONG).show()


        } else {
            toast = Toast(applicationContext)
            view=layoutInflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.viewContainer) )
            toast.view=view
            customToast=view.findViewById(R.id.customToast)
            customToast.text="Invalid login or password. Please try again."
            toast.duration=Toast.LENGTH_LONG
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

          /*  Toast.makeText(applicationContext, "password is wrong", Toast.LENGTH_LONG).show()*/
        }
    }


}


