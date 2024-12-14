package com.example.tifinbox.api



import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import retrofit2.Response
import retrofit2.http.GET

interface ServiceProvider {

    @GET("/api/auth/all-sp")
    suspend fun getAllServiceProvider(): Response<AllServiceProviderModel>
}