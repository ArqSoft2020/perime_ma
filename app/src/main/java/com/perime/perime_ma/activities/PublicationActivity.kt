package com.perime.perime_ma.activities

import android.os.Bundle
import com.perime.perime_ma.R
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

import com.perime.perime_ma.MapsActivity
import androidx.appcompat.app.AppCompatActivity
import apollo.PublicationsQuery
import com.apollographql.apollo.ApolloClient
import com.perime.perime_ma.models.Publication
import com.perime.perime_ma.adapters.PublicationAdapter
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.publication_querys.PublicationQuerys
import kotlinx.android.synthetic.main.activity_publication.*


class PublicationActivity : AppCompatActivity() {

    private lateinit var adapter: PublicationAdapter
    private lateinit var apolloClient: ApolloClient
    private var publications : MutableList<Publication> = mutableListOf<Publication>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)



        apolloClient = ApolloGraphql.setUpApolloClient()
        PublicationQuerys.publicationsQuery(apolloClient) {
            var publicationsGraphql = it.data?.publications!!
            for(publication: PublicationsQuery.Publication? in publicationsGraphql)
                publications.add(Publication(publication!!.title.toString(),publication!!.description.toString(),publication!!.expiration_date.toString(),publication!!.price.toString(), publication!!.categories as List<String>))

            Handler(Looper.getMainLooper()).post(Runnable {
                adapter = PublicationAdapter(this, R.layout.list_item, publications)
                listView.adapter = adapter
            })
        }

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityUserPublication()},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_publications, true)
    }


    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityUserPublication() = startActivity(Intent(this, UserPublication::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))


    fun imageClick(view : View){
        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(applicationContext,"Clickeo la imagen de la X de cerrar", Toast.LENGTH_LONG).show()
        })
    }
}
