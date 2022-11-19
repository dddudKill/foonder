package com.example.homework_1_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val PORTRAIT = "PORTRAIT"
const val LANDSCAPE = "LANDSCAPE"
const val INITIAL_COUNT = 2

class MainActivity : AppCompatActivity(), FragmentToActivityCommunication {

    private var count: Int = INITIAL_COUNT

    private fun getScreenOrientation(): String {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> PORTRAIT
            Configuration.ORIENTATION_LANDSCAPE -> LANDSCAPE
            else -> ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentCount = if (savedInstanceState != null) {
            with(savedInstanceState) {
               getInt("key")
            }
        } else count

        val windowInfo = getScreenOrientation()

        val transaction = supportFragmentManager.beginTransaction()
        when (windowInfo) {
            PORTRAIT -> transaction.replace(R.id.container, createPortraitFragment(currentCount))
            LANDSCAPE -> transaction.replace(R.id.container, createLandscapeFragment(currentCount))
        }
        transaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("key")
    }

    override fun setCountOnMain(count: Int) {
        this.count = count
    }
}


