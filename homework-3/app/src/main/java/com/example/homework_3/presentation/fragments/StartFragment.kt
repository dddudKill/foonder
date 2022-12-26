package com.example.homework_3.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework_3.R

class StartFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginGoogleButton = view.findViewById<ImageView>(R.id.login_google)
        loginGoogleButton.setOnClickListener {
            logIn(true)
        }

        val noLoginButton = view.findViewById<ImageView>(R.id.no_login)
        noLoginButton.setOnClickListener {
            logIn(false)
        }
    }



    private fun logIn(isAuthorized: Boolean) {
        activity?.supportFragmentManager?.let {
            val transaction = it.beginTransaction()
            transaction
                .replace(R.id.fragmentContainer,FridgeFragment.newInstance(isAuthorized))
                .addToBackStack(null)
                .commit()
        }
    }

}