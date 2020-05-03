package com.perime.perime_ma.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!