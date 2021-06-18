package com.example.samplelivedata

data class RecylerList(val items:ArrayList<RecylerData>)
data class RecylerData(val name:String,val description:String,val created_at:String,val owner:Owner,)
data class Owner(val avatar_url:String)