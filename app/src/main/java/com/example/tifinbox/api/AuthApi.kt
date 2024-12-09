package com.example.tifinbox.api


import com.example.tifinbox.model.LoginResponse
import com.example.tifinbox.model.LoginUserModel
import com.example.tifinbox.model.OtpRequest
import com.example.tifinbox.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/auth/register")
    suspend fun createUser(@Body user: User): Response<User>

    @POST("/api/auth/verify")
    suspend fun verifyOTP(@Body otp:OtpRequest): Response<User>

    @POST("/api/auth/login")
    suspend fun loginUser(@Body credential:LoginUserModel):Response<LoginResponse>
}