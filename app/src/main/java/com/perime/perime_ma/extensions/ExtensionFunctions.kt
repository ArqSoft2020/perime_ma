package com.perime.perime_ma.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.view.ViewGroup
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.widget.FrameLayout
import android.view.LayoutInflater
import android.view.MenuItem
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
fun AppCompatActivity.setAllNavigationBarIntentTransitions(mapActivityCallback: () -> Unit, PublicationsCallback: () -> Unit, OrdersActivityCallback: () -> Unit, UserActivityCallback: () -> Unit){
    FindMenuButtonNavigation(R.id.btn_menu_home) { mapActivityCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_publications) { PublicationsCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_orders) { OrdersActivityCallback() }
    FindMenuButtonNavigation(R.id.btn_menu_userprofile) { UserActivityCallback() }
}

/* #############    METHODS TO MODIFY SELECTION FOCUS ON NAVIGATION BAR OR COLOR   ############# */
@SuppressLint("RestrictedApi")
fun AppCompatActivity.focusMenuElement(id_button : Int, flagChecked : Boolean){
    findViewById<FrameLayout>(R.id.menu_activity).findViewById<BottomNavigationView>(R.id.bottom_navigation).menu.findItem(id_button).setChecked(flagChecked)
    //val menu = findViewById<FrameLayout>(R.id.menu_activity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
    //val button = menu.findViewById<BottomNavigationItemView>(id_button)
    //button.setChecked(flagChecked)
}

fun AppCompatActivity.SetColor(color : String, v : MenuItem){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        v.iconTintList = ColorStateList.valueOf(Color.parseColor(color))
    }
    //findViewById<FrameLayout>(R.id.menu_activity).findViewById<BottomNavigationView>(R.id.bottom_navigation).findViewById<BottomNavigationItemView>(R.id.btn_menu_publications).setIconTintList(
    //    ColorStateList.valueOf(Color.parseColor("#6200EE")))
}