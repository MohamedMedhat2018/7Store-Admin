package com.example.firebaseauthwithmvvm.ui.splash

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.VideoView
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.ui.auth.LoginActivity

class Splash_Screen_Activity : AppCompatActivity() {

    lateinit var videoView: VideoView
    lateinit var imageView: ImageView
    lateinit var rl_p: RelativeLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        setContentView(R.layout.activity_splash_screen)


        supportActionBar?.hide()


        videoView = findViewById(R.id.videoView)

        imageView = findViewById(R.id.civ_logo)

        rl_p = findViewById(R.id.rl_p_splash_screen)


        rl_p.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in))

        rl_p.animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                imageView.visibility = View.VISIBLE
                imageView.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in))
            }

            override fun onAnimationStart(p0: Animation?) {}

        })







        val video: Uri =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.hazaz1_video_327)

        videoView.setVideoURI(video)

        videoView.start()

        Handler().postDelayed({
            val test = Intent(this, LoginActivity::class.java)
            startActivity(test)
            finish()
        }, 9000)
    }

}
