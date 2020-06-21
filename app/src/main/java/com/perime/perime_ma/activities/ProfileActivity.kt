package com.perime.perime_ma.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.extensions.SharedPreferences
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File
import java.util.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication()},{goToActivityUserPublication()},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_userprofile, true)

        textView2.text = SharedPreferences.sharedpreferences.userName
        textView3.text = SharedPreferences.sharedpreferences.userEmail
        textView4.text = SharedPreferences.sharedpreferences.userAddress
        textView5.text = SharedPreferences.sharedpreferences.userPhone

        if(!SharedPreferences.sharedpreferences.profilePicture!!.isEmpty()){
            val decodedString: ByteArray = Base64.decode(SharedPreferences.sharedpreferences.profilePicture, Base64.DEFAULT)
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size))
        }
        /* 2da manera, haciendo una petición pero la imagen no se mostrara de inmediato
        val thread = Thread(Runnable {
            try {
                val url = URL("http://34.69.30.153:9000/api/multimedia/${SharedPreferences.sharedpreferences.idUser}/USER")
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                Handler(Looper.getMainLooper()).post(Runnable { imageView.setImageBitmap(image) })

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
        */
    }

    /* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication() = startActivity(Intent(this, PublicationActivity::class.java))
    private fun goToActivityUserPublication() = startActivity(Intent(this, UserPublication::class.java))
    private fun goToActivityProfile() = startActivity(Intent(this, ProfileActivity::class.java))

}
