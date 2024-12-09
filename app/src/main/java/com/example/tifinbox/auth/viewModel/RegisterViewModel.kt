package com.example.tifinbox.auth.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.helper.RegisterFieldState
import com.example.tifinbox.helper.RegisterValidation
import com.example.tifinbox.helper.validateAddress
import com.example.tifinbox.helper.validateName
import com.example.tifinbox.helper.validatePassword
import com.example.tifinbox.helper.validatePhone
import com.example.tifinbox.model.OtpRequest
import com.example.tifinbox.model.User
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class RegisterViewModel:ViewModel() {

    private val authApi = RetrofitInstance.authApi
    private val _register = MutableLiveData<Resource<User>>()
    val register : LiveData<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

    fun registerUser(name:String, phone:String, password:String,address:String){

        val user = User(name,phone,password)

        if(checkValidation(name,phone,password,address)){
            viewModelScope.launch {
                _register.value = Resource.Loading()
                try {
                    val response = authApi.createUser(user)
                    if (response.isSuccessful){
                        _register.value = Resource.Success(user)
                    }else{
                        _register.value = Resource.Error(response.message())
                    }
                }catch (e: Exception){
                    _register.value = Resource.Error(e.message.toString())
                }

            }
        }else{
            val registerFieldState = RegisterFieldState(
                validatePhone(phone), validateName(name), validateAddress(address), validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }



    }

    private fun checkValidation(name: String, phone: String, password: String, address: String): Boolean {
        val phoneValidation = validatePhone(phone)
        val nameValidation = validateName(name)
        val addressValidation = validateAddress(address)
        val passwordValidation = validatePassword(password)

        val shouldRegister = phoneValidation is RegisterValidation.Success
                && nameValidation is RegisterValidation.Success
                && addressValidation is RegisterValidation.Success
                && passwordValidation is RegisterValidation.Success

        return shouldRegister
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