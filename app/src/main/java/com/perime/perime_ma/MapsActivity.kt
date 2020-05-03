package com.perime.perime_ma

import android.os.Bundle
import android.content.Intent

import com.google.android.gms.maps.SupportMapFragment
import com.perime.perime_ma.providers.MapsKotlinProvider

import com.perime.perime_ma.activities.PublicationActivity
import com.perime.perime_ma.activities.FormPublicationActivity
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions

class MapsActivity : MapsKotlinProvider() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityPublication()},{goToActivityProfile()})
    }



    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, FormPublicationActivity::class.java))
}
