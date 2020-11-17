package com.aldi.kebandung.etc

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context : Context) {
    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var IS_LAUNCHED = "IsLaunched"
    val KEY_EMAIL = "username"
    val KEY_PASSWORD = "password"
    private var sharpref : SharedPreferences? = null

    init {
        pref = context.getSharedPreferences("Intro-Slider", Context.MODE_PRIVATE)
        editor = pref!!.edit()
    }

    fun setLaunched(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_LAUNCHED, isFirstTime)
        editor!!.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref!!.getBoolean(IS_LAUNCHED, true)
    }

    fun saveEmail(username : String){
        val editor:SharedPreferences.Editor = sharpref!!.edit()
        editor.putString(KEY_EMAIL, username)
        editor.apply()
    }

    fun savePassword(password : String){
        val editor:SharedPreferences.Editor = sharpref!!.edit()
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }
}
