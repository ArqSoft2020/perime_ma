package com.perime.perime_ma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perime.perime_ma.R
import kotlinx.android.synthetic.main.activity_form_update_publication.*

class FormUpdatePublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_update_publication)

        val title: String = intent.getStringExtra("Title")
        val categories: String = intent.getStringExtra("categories")
        val description: String = intent.getStringExtra("Description")
        val expiration_date: String= intent.getStringExtra("expiration_date")
        val price:  String = intent.getStringExtra("price")

        txt_publication_title.setText(title)
        txt_publication_categories.setText(categories)
        txt_publication_description.setText(description)
        txt_publication_expdate.setText(expiration_date)
        txt_publication_value.setText(price)


    }
}
