package com.me.architecture_study.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.me.architecture_study.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}