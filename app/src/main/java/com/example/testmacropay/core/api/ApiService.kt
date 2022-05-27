package com.example.testmacropay.core.api

import com.example.testmacropay.core.model.LoginResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @POST
    fun getLogin(@Body body: RequestBody, @Url url: String = ""): Call<LoginResponseModel>

}