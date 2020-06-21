package com.perime.perime_ma.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloClient
import com.perime.perime_ma.MapsActivity
import com.perime.perime_ma.R
import com.perime.perime_ma.extensions.SharedPreferences
import com.perime.perime_ma.extensions.focusMenuElement
import com.perime.perime_ma.extensions.setAllNavigationBarIntentTransitions
import com.perime.perime_ma.providers.apollographql.ApolloGraphql
import com.perime.perime_ma.providers.apollographql.multimedia_querys.MultimediaMutations
import com.perime.perime_ma.providers.apollographql.user_querys.UserMutations
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException


class RegisterActivity : AppCompatActivity() {

    private lateinit var apolloClient: ApolloClient
    private var uri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val auth = SharedPreferences.sharedpreferences.authenticated
        uri = null

        setAllNavigationBarIntentTransitions({goToActivityMap()},{goToActivityPublication(auth)},{goToActivityUserPublication(auth)},{goToActivityProfile()})
        focusMenuElement(R.id.btn_menu_userprofile, true)

        btn_register.setOnClickListener(){ register() }
        imageButton.setOnClickListener(){ uploadImage() }
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

                if(uri !== null)
                    uploadImageRequest(it.data?.createUser!!.id_user!!.toString())

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





    private fun uploadImage(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) if (resultCode == Activity.RESULT_OK) {
            val selectedImage = data?.data
            uri = selectedImage
            val filePath = getPath(selectedImage)
            val file_extn = filePath.substring(filePath.lastIndexOf(".") + 1)

            try {
                if (file_extn == "img" || file_extn == "jpg" || file_extn == "jpeg"  || file_extn == "png")
                    imageButton.setImageURI(selectedImage)
                 else
                    Handler(Looper.getMainLooper()).post(Runnable { Toast.makeText(this,"El Archivo Seleccionado No Fue Una Imagen", Toast.LENGTH_LONG).show() })

            } catch (e: FileNotFoundException) {
                Handler(Looper.getMainLooper()).post(Runnable { Toast.makeText(this,"Error del Sistema Interno de archivos", Toast.LENGTH_LONG).show() })
                e.printStackTrace()
            }
        }
    }

    private fun getPath(uri: Uri?): String {
        val projection = arrayOf(MediaColumns.DATA)
        val cursor: Cursor = managedQuery(uri, projection, null, null, null)
        val columnIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }

    private fun uploadImageRequest(id : String){
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)

        apolloClient = ApolloGraphql.setUpApolloClient()
        MultimediaMutations.storeFileMutation(apolloClient, id,"USER",encoded) {}
    }
}
