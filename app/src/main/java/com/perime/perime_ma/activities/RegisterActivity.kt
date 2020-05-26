package com.perime.perime_ma.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.apollographql.apollo.ApolloClient
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.extensions.SharedPreferences
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.user_querys.UserMutations
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val auth = SharedPreferences.sharedpreferences.authenticated

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication(auth)},{goToActivityUserPublication(auth)},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_userprofile, true)

        btn_register.setOnClickListener(){ register() }
    }

    private fun goToActivityMap() = startActivity(Intent(this, MapsActivity::class.java))
    private fun goToActivityPublication(validation:Boolean) {
        if (validation){
            startActivity(Intent(this, PublicationActivity::class.java))
        }else{
            Toast.makeText(this,"No disponible. Por favor Logueate", Toast.LENGTH_LONG).show()
        }
    }
    private fun goToActivityUserPublication(validation: Boolean) {
        if (validation){
            startActivity(Intent(this, UserPublication::class.java))
        }else{
            Toast.makeText(this,"No disponible. Por favor Logueate", Toast.LENGTH_LONG).show()
        }
    }
    private fun goToActivityProfile() = startActivity(Intent(this, LoginActivity::class.java))




    private fun register(){
        val username = txt_username.text.toString()
        val email = txt_email.text.toString()
        val password = txt_password.text.toString()
        val address = txt_address.text.toString()
        val phone = txt_phonenumber.text.toString()
        val flag_user = TextUtils.isEmpty(username)
        val flag_email = TextUtils.isEmpty(email)
        val flag_password = TextUtils.isEmpty(password)
        val flag_address = TextUtils.isEmpty(address)
        val flag_phone = TextUtils.isEmpty(phone)

        if(!flag_user && !flag_email && !flag_password && !flag_address && !flag_phone){
            registerUser(username = username, email = email, password = password, address = address, phone = phone)
        }else{
            if(flag_user)
                txt_username.setError("No puede ser vacio")
            if(flag_email)
                txt_email.setError("No puede ser vacio")
            if(flag_password)
                txt_password.setError("No puede ser vacio")
            if(flag_address)
                txt_address.setError("No puede ser vacio")
            if(flag_phone)
                txt_phonenumber.setError("No puede ser vacio")
        }
    }

    private fun registerUser(username: String, email : String, password: String, address: String, phone : String){
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserMutations.createUserMutation(apolloClient, username, password, address, phone, email) {
            if (it.data?.createUser !== null) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    val toast = Toast.makeText(
                        applicationContext,
                        "USUARIO CREADO, PERO ESPERA A QUE UN ADMIN ACTIVE TU CUENTA",
                        Toast.LENGTH_LONG
                    )
                    val view = toast.view
                    view.setBackgroundColor(Color.BLACK);
                    val text = view.findViewById<View>(android.R.id.message) as TextView
                    text.setTextColor(Color.WHITE);
                    toast.show()
                })
            }else {
                Handler(Looper.getMainLooper()).post(Runnable {
                    val toast = Toast.makeText(
                        applicationContext,
                        "FALLO LA CREACION DEL USUARIO",
                        Toast.LENGTH_LONG
                    )
                    val view = toast.view
                    view.setBackgroundColor(Color.BLACK);
                    val text = view.findViewById<View>(android.R.id.message) as TextView
                    text.setTextColor(Color.WHITE);
                    toast.show()
                })
            }
        }
    }
}
