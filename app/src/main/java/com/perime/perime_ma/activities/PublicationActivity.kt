package com.perime.perime_ma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perime.perime_ma.R
import com.perime.perime_ma.adapters.PublicationAdapter
import com.perime.perime_ma.models.Publication
import kotlinx.android.synthetic.main.activity_publication.*

class PublicationActivity : AppCompatActivity() {

    private lateinit var adapter: PublicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)

        var personList: List<Publication> = listOf(Publication("Azucar","$20","de color blanca"), Publication("Arroz","$10","de color blanca"))
        adapter = PublicationAdapter(this, R.layout.list_item, personList)
        listView.adapter = adapter
    }
}
