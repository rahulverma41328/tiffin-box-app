package com.example.tifinbox.helper

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.tifinbox.R

class CustomFont {

    companion object{
        val customFontFamily = FontFamily(
            Font(R.font.montserrat_regular,FontWeight.Normal),
            Font(R.font.montserrat_bold,FontWeight.Bold)
        )
    }
}