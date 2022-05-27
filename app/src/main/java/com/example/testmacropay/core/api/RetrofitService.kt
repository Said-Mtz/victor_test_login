package com.example.testmacropay.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun instance(): Retrofit = Retrofit.Builder()
        .baseUrl("https://testandroid.macropay.com.mx/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}