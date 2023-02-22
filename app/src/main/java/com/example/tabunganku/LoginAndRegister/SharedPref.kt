package com.example.tabunganku.LoginAndRegister

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity

class SharedPref     (activity: FragmentActivity) {
    val login = "login"
    val myPref = "Main_Pref"
    val sharedPreference : SharedPreferences

    init {
        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status : Boolean){
        sharedPreference.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin() : Boolean{
        return sharedPreference.getBoolean(login, false)
    }
}