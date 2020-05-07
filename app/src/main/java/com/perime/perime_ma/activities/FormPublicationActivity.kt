package com.perime.perime_ma.activities


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.publication_querys.PublicationMutations
import kotlinx.android.synthetic.main.activity_form_publication.*


class FormPublicationActivity : AppCompatActivity() {

    private lateinit var apolloClient: ApolloClient

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_publication)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityUserPublication()},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_publications, true)

        btn_publication_add.setOnClickListener() {
            val title = txt_publication_title.text.toString()
            val category = txt_publication_categories.text.toString()
            val description = txt_publication_description.text.toString()
            val date = txt_publication_expdate.text.toString()
            val price = txt_publication_value.text.toString()
            val flag_title = TextUtils.isEmpty(title)
            val flag_category = TextUtils.isEmpty(category)
            val flag_description = TextUtils.isEmpty(description)
            val flag_date = TextUtils.isEmpty(date)
            val flag_price = TextUtils.isEmpty(price)

            if(!flag_title && !flag_category && !flag_description && !flag_date && !flag_price){
                makePublication(title = title, description = description, expiration_date = date, price = price, categories = category.split(","))
            }else{
                if(flag_title)
                    txt_publication_title.setError("No puede ser vacio")
                if(flag_category)
                    txt_publication_categories.setError("No puede ser vacio")
                if(flag_description)
                    txt_publication_description.setError("No puede ser vacio")
                if(flag_date)
                    txt_publication_expdate.setError("No puede ser vacio")
                if(flag_price)
                    txt_publication_value.setError("No puede ser vacio")
            }
        }
    }

    private fun makePublication(title: String, description: String, state_publication: Boolean = false, contact_information: String = "320412324", id_image: String = "0", stock: String = "1",
                                expiration_date: String, price: String, categories: List<String>){
        Toast.makeText(this,"Realizando petición", Toast.LENGTH_SHORT).show()
        apolloClient = ApolloGraphql.setUpApolloClient()

        PublicationMutations.createPublicationMutation(apolloClient, title, description, state_publication, contact_information, id_image, stock, expiration_date, price, categories) {
            var publication = it.data?.createPublication!!
            Log.w("File: ", publication?.title.toString())

            Handler(Looper.getMainLooper()).post(Runnable {
                Toast.makeText(applicationContext,"Publicación Realizada con Exito", Toast.LENGTH_LONG).show()
            })

        }
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityUserPublication() = startActivity(Intent(this, UserPublication::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))

}
