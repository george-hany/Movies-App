package com.example.android.movieapp.Class

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

const val KEY=1

class SaveSharedPreference (val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(name:String,password:String){
        val editor:SharedPreferences.Editor=sharedPref.edit()

        editor.putString("name",name)
        editor.commit()
        editor.putString("password",password)
        editor.commit()
        editor.putBoolean("status",true)
        editor.commit()
    }

    fun isLogedIn():Boolean=sharedPref.getBoolean("status",false)

    fun logOut(){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.remove("name").apply()
        editor.remove("password").apply()
        editor.remove("status").apply()
    }








}