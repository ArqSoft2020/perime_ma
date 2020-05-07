package com.perime.perime_ma.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apollo.PublicationsQuery
import com.apollographql.apollo.ApolloClient
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.adapters.PublicationAdapter
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.models.Publication
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.publication_querys.PublicationMutations
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
                publications.add(Publication(publication!!._id.toString(), publication!!.title.toString(),publication!!.description.toString(),publication!!.expiration_date.toString(),"$"+publication!!.price.toString(), publication!!.categories as List<String>))

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


    fun imageClickUpdateEvent(view: View){
        val publication =  publications[view.id]
        // LLAMAR AL CAMBIO DE ACTIVITY CON STARTACTIVITY PERO PASANDOLE COMO PARAMETRO DE SU CONTEXTO LA PUBLICACION
    }

    fun imageClickCloseEvent(view : View){
        val publication =  publications[view.id]

        apolloClient = ApolloGraphql.setUpApolloClient()
        PublicationMutations.deletePublicationMutation(apolloClient, publication.id) {

            Handler(Looper.getMainLooper()).post(Runnable {
                publications.removeAt(view.id)
                adapter = PublicationAdapter(this, R.layout.list_item, publications)
                listView.adapter = adapter
                val toast = Toast.makeText(applicationContext,"Publicación ELIMINADA con EXITO", Toast.LENGTH_SHORT)
                val view = toast.view
                view.setBackgroundColor(Color.BLACK);
                val text = view.findViewById<View>(android.R.id.message) as TextView
                text.setTextColor(Color.WHITE);
                toast.show()
            })

        }
    }
}
