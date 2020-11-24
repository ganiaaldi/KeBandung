package com.aldi.kebandung.auth

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.aldi.kebandung.R
import com.aldi.kebandung.etc.AppPreferences
import com.aldi.kebandung.etc.AppPreferences.password
import com.aldi.kebandung.etc.AppPreferences.username
import com.aldi.kebandung.etc.Endpoint
import com.aldi.kebandung.etc.PrefManager
import com.aldi.kebandung.model.User
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject


class LoginFragment : Fragment() {
    private lateinit var prefManager: PrefManager
    private lateinit var preferences: SharedPreferences
    var listUser = ArrayList<User>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton()
        loginMethod()
        prefManager = PrefManager(requireContext())
        fan()
        AppPreferences.init(context!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun loginButton() {
        btnLogin.setOnClickListener {
            val inputUsername = inputLoginUsername.text.toString()
            val inputPasswordUser = inputPasswordLogin.text.toString()
            val checkUsename = listUser.any{ it.usernameUser == inputUsername}
            val checkEmail = listUser.any{ it.emailUser == inputUsername}
            val checkPassword = listUser.any{ it.kataSandiUser == inputPasswordUser}

            if((checkUsename && checkPassword == true) ||(checkEmail && checkPassword == true) ){
                val username = inputUsername
                val password = inputPasswordUser
                if (AppPreferences.isLogin) {
                    AppPreferences.isLogin = false
                    AppPreferences.username = ""
                    AppPreferences.password = ""
                } else {
                    if (username.isNotBlank() && password.isNotBlank()) {
                        AppPreferences.isLogin = true
                        AppPreferences.username = username
                        AppPreferences.password = password
                    } else {
                        Toast.makeText(context, "Login terlebih dahulu", Toast.LENGTH_SHORT).show()
                    }
                }
               onLogin()
                //Toast.makeText(context,"$inputUsername & $inputPasswordUser Berhasil", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Username/Email & Password salah", Toast.LENGTH_SHORT).show()
            }
         // onLogin()
            //Toast.makeText(context,"$inputUsername & $inputPasswordUser", Toast.LENGTH_SHORT).show()
           // Toast.makeText(context,"$email & $username", Toast.LENGTH_SHORT).show()
           // if(activity is AuthActivity){
            //findNavController().navigate(R.id.mainActivity)
      //  } else{
       //         findNavController().navigateUp()
        //    }
        }
        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

    fun loginMethod(){
        //var listUsername = ArrayList<Username>()
        AndroidNetworking.post(Endpoint.READID)
            .addBodyParameter("email",inputLoginUsername.text.toString())
            .addBodyParameter("username",inputLoginUsername.text.toString())
            //.addBodyParameter("nama_lengkap",inputUsernameFull.text.toString())
            .addBodyParameter("kata_sandi",inputPasswordLogin.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    //  loading.dismiss()
                    //Toast.makeText(context,response?.getString("message"),Toast.LENGTH_SHORT).show()

                   // if(response?.getString("message")?.contains("successfully")!!){
                        //  this@RegisterFragment.activity?.finish()
                   // }

                }
                override fun onError(anError: ANError?) {
                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    // Toast.makeText(context, "Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
}

    fun fan(){
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        AndroidNetworking.get(Endpoint.GETID)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listUser.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data User Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        listUser.add(User(
                            jsonObject.getString("email"),
                            jsonObject.getString("username"),
                            jsonObject.getString("nama_lengkap"),
                            jsonObject.getString("kata_sandi")
                        ))

                        if(jsonArray?.length() - 1 == i){
                            loading.dismiss()
                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(context,"Connection Failure",Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun onLogin(){
        //val username : String = inputLoginUsername.text.toString()
        //prefManager.saveEmail(username)
       // val password : String = inputPasswordLogin.text.toString()
       // prefManager.savePassword(password)
        if (AppPreferences.isLogin) {
            findNavController().navigate(R.id.mainActivity)
        } else {
            findNavController().navigate(R.id.authFragment)
        }
    }
}

