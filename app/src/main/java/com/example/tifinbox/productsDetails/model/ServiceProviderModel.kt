package com.example.tifinbox.productsDetails.model

data class ServiceProviderModel(
    val id: Int,
    val name: String,
    val phone: String,
    val password: String,
    val address: String?,
    val orderQuantity: Int,
    val role: String,
    val meal: List<MealModel>,
    val reviews: List<Review>
)
