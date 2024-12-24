package com.example.tifinbox.api

import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceProvider {

    @GET("/api/auth/all-sp")
    suspend fun getAllServiceProvider(@Header("Cookie") cookie: String): Response<AllServiceProviderModel>
}