package com.example.tifinbox.helper

import android.util.Patterns

fun validatePhone(phone:String): RegisterValidation {
    if (phone.length<10)
        return RegisterValidation.Failed("no. should be of 10 numbers")
    return RegisterValidation.Success
}

fun validatePassword(password:String):RegisterValidation{
    if (password.isEmpty())
        return RegisterValidation.Failed("Password cannot be empty")
    if (password.length<6)
        return RegisterValidation.Failed("Password should contains 6 char")

    return RegisterValidation.Success
}

fun validateName(name:String):RegisterValidation{
    if (name.isEmpty()){
        return RegisterValidation.Failed("Name can't be empty")
    }
    if(name.length<3){
        return RegisterValidation.Failed("Name should contain at least 4 characters")
    }
    return RegisterValidation.Success
}
fun validateAddress(address:String): RegisterValidation{
    if (address.isEmpty()){
        return RegisterValidation.Failed("Address can't be empty")
    }
    return RegisterValidation.Success
}