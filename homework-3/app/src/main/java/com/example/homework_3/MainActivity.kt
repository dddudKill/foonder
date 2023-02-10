package com.example.homework_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_3.presentation.fragments.StartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, StartFragment.newInstance())
                .commitNow()
        }
    }
}