package com.example.tifinbox.productsDetails.model

data class ServiceProviderModel(
    val id: String,
    val name : String,
    val phone: String,
    val password : String,
    val address: String,
    val orderQuantity: String,
    val role: String,
    val meal: List<MealModel>,
    val reviews: List<Review>
)
