package com.example.tifinbox.productsDetails.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.helper.StoreUserData
import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ServiceProviderViewModel: ViewModel() {

    private val _getAllSP = MutableStateFlow<Resource<AllServiceProviderModel>>(Resource.Unspecified())
    val getAllSP = _getAllSP
    fun getAllSP(context: Context){

        val userData = StoreUserData(context)
        val serviceProviderApi = RetrofitInstance.createServiceProviderApi(userData)

        Log.e("getAllSP","function started")
        viewModelScope.launch {
           _getAllSP.value =  Resource.Loading()
            val response = serviceProviderApi.getAllServiceProvider()
            Log.e("request send","ture")
            try {
                if (response.isSuccessful){
                    _getAllSP.value = Resource.Success(response.body()!!)
                    Log.e("response", response.body().toString())
                }else{
                    _getAllSP.value = Resource.Error(response.message())
                    Log.e("error",response.toString())
                    Log.e("error",response.toString())
                }
            }catch (e : Exception){
                _getAllSP.value = Resource.Error(response.message())
            }
        }
    }
}