
package com.perime.perime_ma.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.perime.perime_ma.providers.apollographql.session_querys.SessionMutations
import com.perime.perime_ma.providers.apollographql.user_querys.UserQuerys
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_iniciar_sesion.setOnClickListener() {login()}
        btn_registrarse.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
        val auth = SharedPreferences.sharedpreferences.authenticated

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication(auth)},{goToActivityUserPublication(auth)},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_userprofile, true)
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


    private fun login(){
        val email = txt_correo.text.toString()
        val password = txt_contrasena.text.toString()

        if(!email.isEmpty() && !password.isEmpty()){
            graphqlLogin(email, password)
        }else if(email.isEmpty()){
            txt_correo.setError("Usuario no puede ser vacio")
        }else
            txt_contrasena.setError("Contraseña no puede ser vacio")
    }

    private fun graphqlLogin(email: String, password : String){
        apolloClient = ApolloGraphql.setUpApolloClient()
        SessionMutations.sessionmLoginMutation(apolloClient, email, password) {
            Log.w("File: ", it.data.toString())
            if (it.data?.sessionmLogin !== null) {
                var token = it.data?.sessionmLogin!!
                SharedPreferences.sharedpreferences.token = token?.token_session.toString()
                SharedPreferences.sharedpreferences.idUser = token?.id_session!!
                SharedPreferences.sharedpreferences.authenticated = true
                graphqlGetUser(SharedPreferences.sharedpreferences.idUser, SharedPreferences.sharedpreferences.token!!)
            }else{
                Handler(Looper.getMainLooper()).post(Runnable {
                    val toast = Toast.makeText(applicationContext,"ERROR AL LOGUEARSE, USUARIO NO ENCONTRADO", Toast.LENGTH_LONG)
                    val view = toast.view
                    view.setBackgroundColor(Color.BLACK);
                    val text = view.findViewById<View>(android.R.id.message) as TextView
                    text.setTextColor(Color.WHITE);
                    toast.show()
                })
            }
        }
    }

    private fun graphqlGetUser(id: Int, token: String){
        apolloClient = ApolloGraphql.setUpApolloClient()
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserQuerys.getUserQuery(apolloClient, id, token) {
            var user = it.data?.getUser!!
            Log.w("File: ", user?.username_user.toString())
            SharedPreferences.sharedpreferences.userName = user.username_user
            SharedPreferences.sharedpreferences.userEmail = user.email_user
            SharedPreferences.sharedpreferences.userAddress = user.address_user
            SharedPreferences.sharedpreferences.userPhone = user.cellphone_user
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}