package com.aldi.kebandung.auth

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.aldi.kebandung.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authButton()
    }

    private fun authButton() {
        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        btnLoginGuest.setOnClickListener {
            findNavController().navigate(R.id.slideView)
        }
    }
}
