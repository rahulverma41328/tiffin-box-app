package com.example.tifinbox.model

data class MealModel(
    val id: String,
    val day: String,
    val mealType: String,
    val sabji: String,
    val roti: String,
    val rice: String,
    val salad: Boolean,
    val raita: Boolean,
    val description: String,
    val rating: Int,
    val noOfReviews: Int,
    val price: String,
    val createdAt: String,
    val userId: String
)
