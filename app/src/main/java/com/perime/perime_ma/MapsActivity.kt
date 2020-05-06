package com.perime.perime_ma

import android.os.Bundle
import android.content.Intent

import com.google.android.gms.maps.SupportMapFragment
import com.perime.perime_ma.providers.MapsKotlinProvider

import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions


import com.apollographql.apollo.ApolloClient
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.multimedia_querys.MultimediaQuerys
import android.util.Log
import apollo.FilesQuery
import apollo.PublicationsQuery
import com.perime.perime_ma.providers.apollographql.multimedia_querys.MultimediaMutations
import com.perime.perime_ma.providers.apollographql.publication_querys.PublicationMutations
import com.perime.perime_ma.providers.apollographql.publication_querys.PublicationQuerys

import android.widget.Switch
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.perime.perime_ma.activities.*
import com.perime.perime_ma.extensions.focusMenuElement

var confirm:Boolean=false

class MapsActivity : MapsKotlinProvider() {

    private lateinit var apolloClient: ApolloClient

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

        val mFab = findViewById<FloatingActionButton>(R.id.agregarPublicacion)
        mFab.setOnClickListener {
            if (confirm){
                startActivity(Intent(this, FormPublicationActivity::class.java))
            }else{
                Toast.makeText(this,"No estas registrado, Por favor registrate",Toast.LENGTH_LONG).show()
            }

        }

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication(confirm)},{goToActivityUserPublication(confirm)},{goToActivityProfile(confirm)})
        setFocusAllMenuElement()
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
    private fun goToActivityUserPublication(validation: Boolean) {
        if (validation){
            startActivity(Intent(this, UserPublication::class.java))
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

    private fun setFocusAllMenuElement(){
        focusMenuElement(R.id.btn_menu_home, true)
        //focusMenuElement(R.id.btn_menu_orders, false)
        //focusMenuElement(R.id.btn_menu_publications, false)
        //focusMenuElement(R.id.btn_menu_userprofile, false)
    }

}
