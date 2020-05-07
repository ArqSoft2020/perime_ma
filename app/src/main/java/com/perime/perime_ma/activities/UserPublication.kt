package com.perime.perime_ma.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.adapters.PublicationAdapter
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.models.Publication
import kotlinx.android.synthetic.main.activity_publication.*
import kotlinx.android.synthetic.main.activity_user_publication.*
import kotlinx.android.synthetic.main.activity_user_publication.listView

class UserPublication : AppCompatActivity() {

    private lateinit var adapter: PublicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_publication)

        var personList: List<Publication> = listOf(Publication("Kasumi","$10","de color verde","",
            listOf("abc")))
        adapter = PublicationAdapter(this, R.layout.list_item, personList)
        listView.adapter = adapter

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityUserPublication()},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_orders, true)
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityUserPublication() = startActivity(Intent(this, UserPublication::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))

}
