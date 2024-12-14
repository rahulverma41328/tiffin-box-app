package com.example.tifinbox.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val baseUrl = "https://tifin-backend.onrender.com"

    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val authApi: AuthApi = getInstance().create(AuthApi::class.java)
    val serviceProviderApi: ServiceProvider = getInstance().create(ServiceProvider::class.java)
}