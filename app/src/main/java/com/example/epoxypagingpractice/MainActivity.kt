package com.example.epoxypagingpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    override fun onResume() {
        super.onResume()
        val blackView = findViewById<View>(R.id.blackView)
        val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_vertical_shaking)
        blackView.startAnimation(anim)


    }
}