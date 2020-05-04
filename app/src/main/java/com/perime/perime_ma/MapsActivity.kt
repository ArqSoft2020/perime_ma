package com.perime.perime_ma

import android.os.Bundle
import android.content.Intent
import android.util.Log
import apollo.FilesQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

import com.google.android.gms.maps.SupportMapFragment
import com.perime.perime_ma.providers.MapsKotlinProvider

import com.perime.perime_ma.activities.PublicationActivity
import com.perime.perime_ma.activities.FormPublicationActivity
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import okhttp3.OkHttpClient

class MapsActivity : MapsKotlinProvider() {

    private val BASE_URL = "http://ec2-54-88-18-124.compute-1.amazonaws.com:3000/graphql"
    private lateinit var client: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityPublication()},{goToActivityProfile()})

        client = setUpApolloClient()
        Log.w("DEBUG MENSAJE: ", "PROBAAANDO")
        client.newBuilder().build().query(
            FilesQuery()
        ).enqueue(object : ApolloCall.Callback<FilesQuery.Data>(){
            override fun onFailure(e: ApolloException) {
                Log.w("############******-------WWW##  ERROR ---- :", e.message.toString())
            }

            override fun onResponse(response: Response<FilesQuery.Data>) {
                var data = response.data?.files
                Log.w("############******------- WWW##   Graphql  ----  ::: ", data.toString())
            }

        })
    }

    private fun setUpApolloClient(): ApolloClient {

        val okHttp = OkHttpClient
            .Builder()
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttp.build())
            .build()
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, FormPublicationActivity::class.java))
}
