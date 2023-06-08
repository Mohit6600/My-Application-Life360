package com.example.myapplicationlife360.IntroductionPage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.myapplicationlife360.IntroductionPage.CircleCreation.CircleCreate
import com.example.myapplicationlife360.IntroductionPage.Fragment.Driving
import com.example.myapplicationlife360.IntroductionPage.Fragment.Membership
import com.example.myapplicationlife360.IntroductionPage.Fragment.Saftey
import com.example.myapplicationlife360.IntroductionPage.InvitationCode.GiveInvitationCode
import com.example.myapplicationlife360.IntroductionPage.OptionMenu.Message
import com.example.myapplicationlife360.IntroductionPage.OptionMenu.Setting
import com.example.myapplicationlife360.IntroductionPage.SlideUpOption.AddItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myapplicationlife360.R
import com.example.myapplicationlife360.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var permissionCode = 101
    lateinit var dropDownArrow: ImageView
    lateinit var cardView: CardView
    lateinit var layout: LinearLayout
    lateinit var createCircle: Button
    lateinit var navigationBar: BottomNavigationView
    lateinit var setting: ImageButton
    lateinit var message: ImageButton
    lateinit var bottomSheet: FrameLayout
    lateinit var searchBtn: LinearLayout
    private lateinit var layout17: LinearLayout
    lateinit var addPerson: ImageButton
    lateinit var layout19: RelativeLayout
    lateinit var key: ImageButton
    lateinit var layout27:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_maps)*/


        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.navigationBar.setOnItemSelectedListener {

            when (it.itemId) {


                R.id.driving -> replaceFragment(Driving())
                R.id.safety -> replaceFragment(Saftey())
                R.id.membership -> replaceFragment(Membership())


                else -> {

                }

            }

            true
        }



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.gan
        getCurrentLocationUser()



        dropDownArrow = findViewById(R.id.dropDownArrow)
        cardView = findViewById(R.id.cardView)
        layout = findViewById(R.id.layout)
        createCircle = findViewById(R.id.createCircle)
        navigationBar = findViewById(R.id.navigationBar)
        setting = findViewById(R.id.setting)
        message = findViewById(R.id.message)
        val location = findViewById<View>(R.id.location)
        bottomSheet = findViewById(R.id.bottomSheet)
        searchBtn = findViewById(R.id.searchLayout)
        layout17 = findViewById(R.id.layout17)
        addPerson = findViewById(R.id.addPersonSign)
        layout19 = findViewById(R.id.layout19)
        key = findViewById(R.id.key)
        layout27=findViewById(R.id.layout27)

        arrowSign()
        circleCreate()
        goToSetting()
        goToMessage()
        allSlideUpOption()
        slideUppBtn()


        //for getting back to map

        location.setOnClickListener() {
            intent = Intent(applicationContext, MapsActivity::class.java)
            startActivity(intent)
        }


        // it is used for bottom slide up option
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 650
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {

        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Current Location")

        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7f))
        googleMap?.addMarker(markerOptions)
        /* mMap = googleMap

         // Add a marker in Sydney and move the camera
         val sydney = LatLng(-34.0, 151.0)
         mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
         mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    private fun getCurrentLocationUser() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode
            )
            return
        }
        val getLocation =
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    currentLocation = location
                    Toast.makeText(
                        applicationContext, currentLocation.latitude.toString() + "" +
                                currentLocation.latitude.toString(), Toast.LENGTH_LONG
                    ).show()
                    val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)

                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                getCurrentLocationUser()
            }

        }
    }

    private fun arrowSign() {

        var clickCount = 0

        searchBtn.setOnClickListener() {

            clickCount++
            System.out.println(clickCount)
            if (clickCount % 2 == 1) {
                layout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
                val animation = TranslateAnimation(0f, 0f, -500f, 0f)
                animation.duration = 500
                animation.fillAfter = true
                cardView.startAnimation(animation)

                dropDownArrow.animate().rotation(180f).start()


            } else {
                Log.d("ArrowSign", "clickCount: $clickCount")

                val animation = TranslateAnimation(0f, 0f, 0f, -550f)
                animation.duration = 500
                animation.fillAfter = true
                cardView.startAnimation(animation)

                dropDownArrow.animate().rotation(0f).start()
            }

        }

    }

    private fun circleCreate() {

        createCircle.setOnClickListener() {

            intent = Intent(applicationContext, CircleCreate::class.java)
            startActivity(intent)

        }

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }

    private fun goToSetting() {

        setting.setOnClickListener() {


            intent = Intent(applicationContext, Setting::class.java)
            startActivity(intent)
        }

    }

    private fun goToMessage() {

        message.setOnClickListener() {


            intent = Intent(applicationContext, Message::class.java)
            startActivity(intent)
        }

    }

    private fun allSlideUpOption() {


//it is used for give invitation code while arrow slide down
        layout17.setOnClickListener() {

            intent = Intent(applicationContext, GiveInvitationCode::class.java)

            startActivity(intent)

        }

        //it is used for give invitation code while slide up option

        addPerson.setOnClickListener() {

            intent = Intent(applicationContext, GiveInvitationCode::class.java)

            startActivity(intent)

        }

        layout19.setOnClickListener() {

            intent = Intent(applicationContext, AddItem::class.java)

            startActivity(intent)

        }


    }


    private fun slideUppBtn() {

        key.setOnClickListener() {

            layout17.visibility = View.INVISIBLE



            val layoutParams = layout19.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = 250 // Adjust the negative margin value to shift the TextView further above
            layout19.layoutParams = layoutParams

            val layoutParams1 = layout27.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams1.topMargin = 85 // Adjust the negative margin value to shift the TextView further above
            layout27.layoutParams = layoutParams

        }
    }


}




