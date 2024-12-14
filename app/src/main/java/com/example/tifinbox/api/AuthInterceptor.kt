package com.example.tifinbox.api

import androidx.datastore.core.DataStore
import com.example.tifinbox.helper.StoreUserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val dataStore: StoreUserData): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token: String? = null

        runBlocking {
            token = dataStore.jwtToken.first()
        }

        val request = chain.request().newBuilder()
        if (!token.isNullOrBlank()){
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }
}