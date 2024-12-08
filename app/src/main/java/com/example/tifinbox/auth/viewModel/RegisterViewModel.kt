package com.example.tifinbox.auth.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.model.OtpRequest
import com.example.tifinbox.model.User
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel:ViewModel() {

    private val authApi = RetrofitInstance.authApi

    fun registerUser(name:String, phone:String, password:String){
        val user = User(name,phone,password)
        Log.e("phone no:",phone)
        viewModelScope.launch {
            try {
                val response = authApi.createUser(user)

                if (response.isSuccessful){
                    Log.e("response",response.toString())
                }else{
                    Log.e("response",response.toString())
                }
            }catch (e: Exception){
                Log.e("response",e.message.toString())
            }

        }

    }

    fun verifyOTP(otp:String){
        Log.e("otp",otp)
        viewModelScope.launch {
            val response = authApi.verifyOTP(OtpRequest(otp = otp))
            try {
                if (response.isSuccessful){
                    Log.e("response verify",response.toString() )
                }else{
                    Log.e("response verify",response.toString() )
                }
            }catch (e: Exception){
                Log.e("response verify",e.message.toString() )
            }
        }
    }
}