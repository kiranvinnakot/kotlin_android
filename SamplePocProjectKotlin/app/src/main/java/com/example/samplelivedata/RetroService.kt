package com.example.samplelivedata

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    //repositories?q=network
    @GET("repositories")
  fun getDataFromAPI(@Query("q")query : String):retrofit2.Call<RecylerList>
}