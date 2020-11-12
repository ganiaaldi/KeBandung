package com.aldi.kebandung.auth

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.aldi.kebandung.etc.Endpoint

import com.aldi.kebandung.R
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject

class RegisterFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    fun formRegister(){
        val loading = ProgressDialog(context)
        loading.setMessage("Menambahkan pengguna..")
        loading.show()
        AndroidNetworking.post(Endpoint.REGISTER)
            .addBodyParameter("email",inputEmail.text.toString())
            .addBodyParameter("username",inputUsername.text.toString())
            .addBodyParameter("nama_lengkap",inputUsernameFull.text.toString())
            .addBodyParameter("kata_sandi",inputPassword.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(context,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@RegisterFragment.activity?.finish()
                    }

                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                  //  Toast.makeText(context,"Connection Failure", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context,"Pengguna berhasil didaftarkan", Toast.LENGTH_SHORT).show()
                    //this@RegisterFragment.activity?.finish()
                    findNavController().navigate(R.id.loginFragment)
                }
            })

    }


    private fun registerButton(){
        btnRegister.setOnClickListener {
            if(inputEmail.text.toString().equals("") || inputUsername.text.toString().equals("")
                || inputUsernameFull.text.toString().equals("")
                || inputPassword.text.toString().equals("") || inputPasswordConfirm.text.toString().equals("")){
                Toast.makeText(context,"Isi form terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
            else {
                if(inputPassword.text.toString() == inputPasswordConfirm.text.toString()){
                   formRegister()
                } else{
                    Toast.makeText(context,"Kata sandi harus sama dengan konfirmasi kata sandi!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonBackk.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
