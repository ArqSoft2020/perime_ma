package com.perime.perime_ma

import android.os.Bundle
import android.content.Intent

import com.google.android.gms.maps.SupportMapFragment
import com.perime.perime_ma.providers.MapsKotlinProvider

import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions

import android.widget.Switch
import android.widget.Toast
import com.perime.perime_ma.activities.*

var confirm:Boolean=false
class MapsActivity : MapsKotlinProvider() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val sw1 = findViewById<Switch>(R.id.switch1)
        sw1?.setOnCheckedChangeListener({ _, isChecked ->
            if (isChecked) {
                    Toast.makeText(this,"Estas registrado, Accede a tu perfil",Toast.LENGTH_LONG).show()
                    confirm=true
                } else {
                    Toast.makeText(this,"No estas registrado, Por favor registrate",Toast.LENGTH_LONG).show()
                    confirm=false
                }
            })

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication(confirm)},{goToActivityPublication(confirm)},{goToActivityProfile(confirm)})
    }



    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication(validation:Boolean) {
        if (validation){
            startActivity(Intent(this, PublicationActivity::class.java))
        }else{
            Toast.makeText(this,"Usuario no identificado, Por favor registrate",Toast.LENGTH_LONG).show()
        }
    }
    private fun goToActivityProfile(validation:Boolean){
        if (validation){
            startActivity(Intent(this, ProfileActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }



}
