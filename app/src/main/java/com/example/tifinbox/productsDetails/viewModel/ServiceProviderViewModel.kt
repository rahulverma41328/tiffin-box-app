package com.example.tifinbox.productsDetails.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tifinbox.api.ServiceProvider
import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import com.example.tifinbox.productsDetails.model.MealModel
import com.example.tifinbox.productsDetails.model.ServiceProviderModel
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceProviderViewModel: ViewModel() {


    private val _getAllSP = MutableStateFlow<Resource<AllServiceProviderModel>>(Resource.Unspecified())
    val getAllSP = _getAllSP

    fun getAllSP(cookie:String){
        _getAllSP.value = Resource.Loading()
        viewModelScope.launch {
           _getAllSP.value =  Resource.Loading()
//            val cookieLast = cookie.trim()
//            Log.e("cookie system",cookieLast)
//            val response = RetrofitInstance.serviceProviderApi.getAllServiceProvider(cookieLast)
//            Log.e("response",response.toString())
//            try {
//                if (response.isSuccessful){
//                    _getAllSP.value = Resource.Success(response.body()!!)
//                    Log.e("response", response.body().toString())
//                }else{
//                    _getAllSP.value = Resource.Error(response.message())
//                    Log.e("error",response.message())
//                }
//            }catch (e : Exception){
//                _getAllSP.value = Resource.Error(response.message())
//            }

            _getAllSP.value = Resource.Success(dummyData())
        }
    }

    private fun dummyData(): AllServiceProviderModel{
        return AllServiceProviderModel(
            success = true,
            sp = listOf(
                ServiceProviderModel(
                    id = 1,
                    name = "Shalu Kitchen",
                    phone = "+918791701281",
                    password = "$2b$10$4lB3Lhg.A6jkuokSwsJcSOVblNrzMVFDr9CuzKK4hXsNyrW1yDqc.",
                    address = "slkdjflsdjlkalsdjkafldajlsdkfjaksld",
                    orderQuantity = 10,
                    role = "SP",
                    meal = listOf(
                        MealModel(
                            id = "1",
                            day = "SUNDAY",
                            mealType = "LUNCH",
                            sabji = "aalu ki sabji",
                            roti = "5",
                            rice = "han he he",
                            salad = false,
                            raita = true,
                            description = "this is description",
                            rating = 5,
                            noOfReviews = 1,
                            price = "50",
                            createdAt = "2024-12-11T12:34:56.000Z",
                            userId = "1"
                        ),
                        MealModel(
                            id = "2",
                            day = "MONDAY",
                            mealType = "LUNCH",
                            sabji = "ghobhi ki sabji",
                            roti = "4",
                            rice = "han he",
                            salad = false,
                            raita = false,
                            description = "null",
                            rating = 0,
                            noOfReviews = 1,
                            price = "50",
                            createdAt = "2024-12-11T12:34:56.000Z",
                            userId = "1"
                        )
                    ),
                    reviews = emptyList()
                ),
                ServiceProviderModel(
                    id = 3,
                    name = "Shyam lover Kitchen sdkflsdjsjkl",
                    phone = "+917900482042",
                    password = "sdkjfkdsjbne",
                    address = "skdjfals sla jflj la jadjfal jfakldfaj ja alsdajkasl jal jaljdfa djfladfja lkjls sakdjfasjd lasdlkasj faklajdsafj al sdjflk sjldj lasdjflkajl sdjfla jfalk fasjdkfakdf jl",
                    orderQuantity = 0,
                    role = "SP",
                    meal = emptyList(),
                    reviews = emptyList()
                )
            )
        )
    }
}