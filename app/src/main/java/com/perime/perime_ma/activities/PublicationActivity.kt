package com.perime.perime_ma.activities

import android.os.Bundle
import com.perime.perime_ma.R
import android.content.Intent

import com.perime.perime_ma.MapsActivity
import androidx.appcompat.app.AppCompatActivity
import com.perime.perime_ma.models.Publication
import com.perime.perime_ma.adapters.PublicationAdapter
import kotlinx.android.synthetic.main.activity_publication.*
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions


class PublicationActivity : AppCompatActivity() {

    private lateinit var adapter: PublicationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)

        var personList: List<Publication> = listOf(Publication("Azucar","$20","de color blanca"), Publication("Arroz","$10","de color blanca"))
        adapter = PublicationAdapter(this, R.layout.list_item, personList)
        listView.adapter = adapter

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityUserPublication()},{goToActivityProfile()})
    }


    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityUserPublication() = startActivity(Intent(this, UserPublication::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))
}
