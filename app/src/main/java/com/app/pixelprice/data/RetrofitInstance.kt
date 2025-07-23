package com.app.pixelprice.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_url = "https://www.cheapshark.com/api/1.0/"



    private val retrofit by lazy { Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory
            .create())
        .build()
    }

    val gameAPI: IGameAPI = retrofit.create(IGameAPI::class.java)

}