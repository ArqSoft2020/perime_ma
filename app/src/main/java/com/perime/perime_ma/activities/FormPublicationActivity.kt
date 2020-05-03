package com.perime.perime_ma.activities


import android.os.Bundle
import android.content.Intent
import com.perime.perime_ma.R

import com.perime.perime_ma.MapsActivity
import androidx.appcompat.app.AppCompatActivity
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions

class FormPublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_publication)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityPublication()},{goToActivityProfile()})
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, FormPublicationActivity::class.java))
}
