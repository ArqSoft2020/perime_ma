package com.perime.perime_ma.extensions

import android.app.Activity
import android.view.ViewGroup
import android.content.Intent
import android.widget.FrameLayout
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationItemView

import com.perime.perime_ma.R

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!
inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}







/* #############    ALL CONFIGURATION TO INTENTS TRANSITIONS - NAVIGATION BAR   ############# */
fun AppCompatActivity.FindMenuButtonNavigation(id_button : Int, clickCallback: () -> Unit){
    val menu = findViewById<FrameLayout>(R.id.menu_activity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
    val button = menu.findViewById<BottomNavigationItemView>(id_button)
    button.setOnClickListener { clickCallback() }
}
fun AppCompatActivity.setAllNavigationBarIntentTransitions(mapActivityCallback: () -> Unit, OrdersActivityCallback: () -> Unit, PublicationsCallback: () -> Unit, UserActivityCallback: () -> Unit){
    FindMenuButtonNavigation(R.id.btn_menu_home) { mapActivityCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_orders) { OrdersActivityCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_publications) { PublicationsCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_userprofile) { UserActivityCallback() }
}