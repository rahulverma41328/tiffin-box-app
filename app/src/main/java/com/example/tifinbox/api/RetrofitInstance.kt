package com.example.tifinbox.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val baseUrl = "https://tifin-backend.onrender.com"

<<<<<<< HEAD
    private fun getInstance(storeUserData: StoreUserData) : Retrofit{

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(storeUserData)) // Add AuthInterceptor
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
=======
    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
>>>>>>> parent of 3a947fa (got jwt token and fine)
    }

    fun createAuthApi(userData: StoreUserData): AuthApi{
        return getInstance(userData).create(AuthApi::class.java)
    }

    fun createServiceProviderApi(userData: StoreUserData): ServiceProvider{
        return getInstance(userData).create(ServiceProvider::class.java)
    }


}