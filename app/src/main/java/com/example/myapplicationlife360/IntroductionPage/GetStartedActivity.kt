package com.example.myapplicationlife360.IntroductionPage

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationlife360.IntroductionPage.LoginProcess.SignInWithNumber
import com.example.myapplicationlife360.IntroductionPage.RegistrationProcess.RegisterMobileNo
import com.example.myapplicationlife360.R

class GetStartedActivity : AppCompatActivity() {

    lateinit var indicatorContainer: LinearLayout   //indicatorContainer will be initialized later that's why we used late init
    lateinit var signIn: Button
    lateinit var getStartedBtn: Button

    // it is a data for description that is given in Introduction page
    val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Share your location with your family in real-time"
            ), IntroSlide(
                "Coordinate easily with smart notifications"
            ), IntroSlide(
                "Encourage safer driving with reports on speed, texting, and more"
            )
        )

    )

    // it is initialize globally
    var videoView2: VideoView? = null
    var mediaController: MediaController? = null
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        indicatorContainer = findViewById(R.id.indicatorContainer)
        signIn = findViewById(R.id.signIn)
        getStartedBtn = findViewById(R.id.getStartedBtn)

        val introViewPager = findViewById<ViewPager2>(R.id.viewPager)
        introViewPager.adapter = introSliderAdapter

        // from now it is done for video in intro page
        videoView2 = findViewById<View>(R.id.videoView) as VideoView?

        if (mediaController == null) {
            mediaController = MediaController(this)
            mediaController!!.setAnchorView(this.videoView2)
        }


        /*      videoView2!!.setMediaController(mediaController)  */          // it is used to show the video controller of video


        videoView2!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.myvideo))



        videoView2!!.requestFocus()

        videoView2!!.start()     // while removing this nothing happen

        // it is used when video is full completed
//        videoView2!!.setOnCompletionListener {
//            Toast.makeText(applicationContext, "video end", Toast.LENGTH_LONG).show()
//        }

        // it is used to know the error in the tym of running a video
        videoView2!!.setOnErrorListener { mediaPlayer, i, i2 ->
            Toast.makeText(applicationContext, "Error occured", Toast.LENGTH_LONG).show()
            false

        }

        // it is used to loop the video again and again
        videoView2!!.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer?) {
                //Start Playback
                videoView2!!.start()        // while removing this nothing happen
                //Loop Video
                mp!!.isLooping = true;

            }
        });

        setUpIndicator()
        setActiveIndicator(0)

        // it is used to see which slide we have and shown by the indicator we used

        introViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setActiveIndicator(position)
            }
        }
        )
// it is used to switch the window by clicking sign in

        signIn.setOnClickListener() {

            intent = Intent(this, SignInWithNumber::class.java)
            startActivity(intent)
        }

        // it is used to switch the window by clicking get started

        getStartedBtn.setOnClickListener() {
            intent = Intent(this, RegisterMobileNo::class.java)
            startActivity(intent)
        }

        // it is used to switch the window by clicking sign in with email



    }

    fun setUpIndicator() {

        /*  val indicatorContainer = findViewById<LinearLayout>(R.id.indicatorContainer)*/


        val indicator = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext.applicationContext, R.drawable.indicatior_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }

            indicatorContainer.addView(indicator[i])
        }
    }

    fun setActiveIndicator(index: Int) {


        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicatior_inactive

                    )
                )
            }

        }
    }
}
