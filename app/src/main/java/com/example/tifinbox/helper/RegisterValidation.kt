package com.example.tifinbox.helper

import android.provider.ContactsContract.CommonDataKinds.Phone

sealed class RegisterValidation {
    object Success: RegisterValidation()
    data class Failed(val message:String): RegisterValidation()
}

data class RegisterFieldState(
    val phone: RegisterValidation,
    val name: RegisterValidation,
    val address: RegisterValidation,
    val password: RegisterValidation
)