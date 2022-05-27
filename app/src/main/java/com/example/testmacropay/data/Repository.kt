package com.example.testmacropay.data

import android.util.Log
import androidx.lifecycle.Observer
import com.auth0.android.jwt.JWT
import com.example.testmacropay.core.api.ApiService
import com.example.testmacropay.core.api.RetrofitService
import com.example.testmacropay.core.model.BodyCleamsModel
import com.example.testmacropay.core.model.KeysMapModel
import com.example.testmacropay.ui.StatusRequest
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.await

class Repository {

    private val retrofit: Retrofit = RetrofitService.instance()
    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun requestBody(
        email: String,
        password: String,
        observer: Observer<Pair<StatusRequest, BodyCleamsModel?>>
    ) {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("password", password)
            .build()

        val response = apiService.getLogin(requestBody).await()
        if (response.success) {

            response.toString().log("bodyResponse")
            val dataResponseJwt = decrypt(response.token)
            val claims = dataResponseJwt.claims.toMap().toList()
                .map { Pair(it.first ?: "", it.second.asString() ?: "") }.toMap()

            observer.onChanged(
                Pair(
                    StatusRequest.SUCCESS, BodyCleamsModel(
                        titular = claims[KeysMapModel.TITULAR.minus] ?: "",
                        url = claims[KeysMapModel.URL.minus] ?: "",
                        email = claims[KeysMapModel.EMAIL.minus] ?: "",
                        solicitud = claims[KeysMapModel.REQUEST.minus] ?: ""
                    )
                )
            )

        } else {
            observer.onChanged(Pair(StatusRequest.FAILURE, null))
        }
    }

    private fun decrypt(token: String) = JWT(token)

    fun String.log(key: String) {
        Log.e(key, this)
    }
}