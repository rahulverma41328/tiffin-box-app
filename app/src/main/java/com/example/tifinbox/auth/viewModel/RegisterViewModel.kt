package com.example.tifinbox.auth.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.helper.RegisterFieldState
import com.example.tifinbox.helper.RegisterValidation
import com.example.tifinbox.helper.StoreUserData
import com.example.tifinbox.helper.validateAddress
import com.example.tifinbox.helper.validateName
import com.example.tifinbox.helper.validatePassword
import com.example.tifinbox.helper.validatePhone
import com.example.tifinbox.model.LoginResponse
import com.example.tifinbox.model.LoginUserModel
import com.example.tifinbox.model.OtpRequest
import com.example.tifinbox.model.User

import com.example.tifinbox.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import java.lang.Exception

class RegisterViewModel:ViewModel() {


    private val _register = MutableLiveData<Resource<User>>()
    val register : LiveData<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

    private val _checkOtp = MutableStateFlow<Resource<Boolean>>(Resource.Unspecified())
    val checkOtp = _checkOtp

    private val _login = MutableStateFlow<Resource<LoginResponse>>(Resource.Unspecified())
    val login = _login

    fun registerUser(name:String, phone:String, password:String,address:String,context: Context){

        val user = User(id = 0,name = name, phone = phone, password = password, address =  address)
        val userData = StoreUserData(context)
        val authApi = RetrofitInstance.createAuthApi(userData)
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
        Log.e("validation","started")
        val phoneValidation = validatePhone(phone)
        val nameValidation = validateName(name)
        val addressValidation = validateAddress(address)
        val passwordValidation = validatePassword(password)

        val shouldRegister = phoneValidation is RegisterValidation.Success
                && nameValidation is RegisterValidation.Success
                && addressValidation is RegisterValidation.Success
                && passwordValidation is RegisterValidation.Success

        Log.e("validation",shouldRegister.toString())
        return shouldRegister
    }

    fun verifyOTP(otp:String,context: Context){
        val userData = StoreUserData(context)
        val authApi = RetrofitInstance.createAuthApi(userData)
        viewModelScope.launch {
            if(otp.isNotEmpty()) {
                val response = authApi.verifyOTP(OtpRequest(otp = otp))
                try {
                    if (response.isSuccessful) {
                        _checkOtp.value = Resource.Success(true)
                    } else {
                        _checkOtp.value = Resource.Error(response.message().toString())
                    }
                } catch (e: Exception) {
                    _checkOtp.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    fun loginUser(phone: String, password:String, context: Context){

         val storeUserData = StoreUserData(context)
         val authApi = RetrofitInstance.createAuthApi(storeUserData)

        _login.value = Resource.Loading()
        viewModelScope.launch {
            if (phone.isNotEmpty() && password.isNotEmpty()){
                val response = authApi.loginUser(LoginUserModel(phone, password))
                try {
                    if (response.isSuccessful){
                        saveUserData(response.body(),storeUserData)
                        getCookies(response.headers(),storeUserData)
                        response.body()?.let {
                            val user: LoginResponse = response.body()!!
                            _login.value = Resource.Success(user)
                        }
                    }
                    else{
                        _login.value = Resource.Error(response.message())
                    }
                }catch (e: Exception){
                    _login.value = Resource.Error(response.message())
                }
            }
        }
    }

    private suspend fun saveUserData(body: LoginResponse?,storeUserData: StoreUserData) {
        if (body != null) {
            if (storeUserData.hasData()){
                storeUserData.clearData()
                Log.e("has data","ture")
            }
            else{
                Log.e("has data", "false")
                storeUserData.saveUserData(body.user.name,body.user.phone,body.user.address, isLogin = true)
            }

        }
    }


    private suspend fun getCookies(headers: Headers,storeUserData: StoreUserData) {
        val cookies = headers["Set-Cookie"]
        if (!cookies.isNullOrBlank()){
            Log.e("cookies",cookies)
            storeUserData.saveJWTToken(token = cookies)
        }
        storeUserData.jwtToken.collect{
            Log.e("jwt token ", it)
        }
    }

}