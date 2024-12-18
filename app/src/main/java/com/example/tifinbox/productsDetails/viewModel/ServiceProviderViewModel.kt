package com.example.tifinbox.productsDetails.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.model.AllServiceProviderModel
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ServiceProviderViewModel: ViewModel() {

    private val serviceProviderApi = RetrofitInstance.serviceProviderApi

    private val _getAllSP = MutableStateFlow<Resource<AllServiceProviderModel>>(Resource.Unspecified())
    val getAllSP = _getAllSP

    init {
        getAllSP()
    }

    fun getAllSP(){
        viewModelScope.launch {
           _getAllSP.value =  Resource.Loading()
            val response = serviceProviderApi.getAllServiceProvider()
            try {
                if (response.isSuccessful){
                    _getAllSP.value = Resource.Success(response.body()!!)
                    Log.e("response", response.body().toString())
                }else{
                    _getAllSP.value = Resource.Error(response.message())
                    Log.e("response",response.message().toString())
                }
            }catch (e : Exception){
                _getAllSP.value = Resource.Error(response.message())
            }
        }
    }
}