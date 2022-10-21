package com.example.homework_1

import android.app.ActionBar.LayoutParams
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var leftCounter = 0
        var centerCounter = 0
        var rightCounter = 0

        val btnAddLeft = findViewById<Button>(R.id.btn_add_1)
        btnAddLeft.setOnClickListener {
            leftCounter++
            val layout = findViewById<LinearLayout>(R.id.layout_left)
            add(layout, leftCounter)
        }
        val btnAddCenter: Button = findViewById(R.id.btn_add_2)
        btnAddLeft.setOnClickListener {
            centerCounter++
            val layout = findViewById<LinearLayout>(R.id.layout_center)
            add(layout, centerCounter)
        }
        val btnAddRight: Button = findViewById(R.id.btn_add_3)
        btnAddLeft.setOnClickListener {
            rightCounter++
            val layout = findViewById<LinearLayout>(R.id.layout_right)
            add(layout, rightCounter)
        }
    }
}

fun add(layout: LinearLayout, number: Int) {
    layout.addView(TextView())
}

fun createNewTextView() : TextView {
    val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    val textView = TextView()
    textView.setLayoutParms
}