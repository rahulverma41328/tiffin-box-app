package com.example.tifinbox.productsDetails.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceProviderViewModel: ViewModel() {


    private val _getAllSP = MutableStateFlow<Resource<AllServiceProviderModel>>(Resource.Unspecified())
    val getAllSP = _getAllSP

    fun getAllSP(cookie:String){
        viewModelScope.launch {
           _getAllSP.value =  Resource.Loading()
            val cookieLast = cookie.trim()
            val response = RetrofitInstance.serviceProviderApi.getAllServiceProvider(cookieLast)
            try {
                if (response.isSuccessful){
                    _getAllSP.value = Resource.Success(response.body()!!)
                    Log.e("response", response.body().toString())
                }else{
                    _getAllSP.value = Resource.Error(response.message())
                    Log.e("error",response.message())
                }
            }catch (e : Exception){
                _getAllSP.value = Resource.Error(response.message())
            }
        }
    }
}