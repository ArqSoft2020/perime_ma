package com.perime.perime_ma.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.extensions.FindMenuButtonNavigation
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityPublication()},{goToActivityProfile()})
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))
}
