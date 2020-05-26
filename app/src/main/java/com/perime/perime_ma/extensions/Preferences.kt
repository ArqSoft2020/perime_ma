package com.perime.perime_ma.extensions

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    val PREFS_NAME = "com.permie.sharedpreferences"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var token: String?
        get() = prefs.getString("token", "")
        set(value) = prefs.edit().putString("token", value).apply()

    var idUser : Int
        get() = prefs.getInt("id",-1)
        set(value) = prefs.edit().putInt("id", value).apply()

    var authenticated : Boolean
        get() = prefs.getBoolean("Authenticated", false)
        set(value) = prefs.edit().putBoolean("Authenticated", value).apply()

    var userName: String?
        get() = prefs.getString("username", "")
        set(value) = prefs.edit().putString("username", value).apply()

    var userEmail: String?
        get() = prefs.getString("useremail", "")
        set(value) = prefs.edit().putString("useremail", value).apply()

    var userAddress: String?
        get() = prefs.getString("useraddress", "")
        set(value) = prefs.edit().putString("useraddress", value).apply()

    var userPhone: String?
        get() = prefs.getString("userphone", "")
        set(value) = prefs.edit().putString("userphone", value).apply()
}