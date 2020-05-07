package com.perime.perime_ma.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.perime.perime_ma.models.Publication
import com.perime.perime_ma.extensions.inflate
import kotlinx.android.synthetic.main.list_item.view.*

class PublicationAdapter(val context: Context, val layout: Int, val list: List<Publication>) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val vh: PublicationViewHolder

        if (convertView == null) {
            view = parent.inflate(layout)
            vh = PublicationViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as PublicationViewHolder
        }

        vh.title.text = "${list[position].title}"
        vh.price.text = "$${list[position].price}"
        vh.description.text = "${list[position].description}"
        vh.imageView2.id = position
        vh.imageView3.id = position

        return view
    }
}

private class PublicationViewHolder(view: View) {
    val title: TextView = view.textViewTitle
    val price: TextView = view.textViewPrice
    val description: TextView = view.textViewDescription

    val imageView2: ImageView = view.imageView2
    val imageView3: ImageView = view.imageView3
}