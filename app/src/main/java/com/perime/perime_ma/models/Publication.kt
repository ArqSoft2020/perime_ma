package com.perime.perime_ma.models

data class Publication (val id : String, val title: String, val description : String, val expiration_date : String, val price : String, val categories: List<String>){}