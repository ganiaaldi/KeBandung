package com.aldi.kebandung.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.aldi.kebandung.R
import com.aldi.kebandung.etc.ChangeToolbarTitle
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(true)
        (activity as ChangeToolbarTitle).updateTitle("Akun")
        buttonLogin()
        createDestination()
    }

    fun buttonLogin(){
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment2)
            (activity as ChangeToolbarTitle).showToolbar(false)
        }
    }

    private fun createDestination(){
        tvTambahTempat.setOnClickListener {
           findNavController().navigate(R.id.createDestination)
            (activity as ChangeToolbarTitle).showToolbar(false)
        }
    }
}
