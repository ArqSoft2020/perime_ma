package com.perime.perime_ma.extensions

import android.app.Application

class SharedPreferences : Application() {

        companion object {
            lateinit var sharedpreferences: Preferences
        }

        override fun onCreate() {
            super.onCreate()
            sharedpreferences = Preferences(applicationContext)
            sharedpreferences.authenticated = false
            sharedpreferences.idUser = -1
            sharedpreferences.token = ""
            sharedpreferences.profilePicture = ""
        }
}