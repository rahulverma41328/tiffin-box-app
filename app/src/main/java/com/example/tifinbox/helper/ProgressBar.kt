package com.example.tifinbox.helper

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tifinbox.ui.theme.appGreen

@Composable
fun ShowProgressBar(progress: Boolean){
    if (progress){
        CircularProgressIndicator(
            modifier = Modifier.width(20.dp).height(20.dp),
            color = appGreen
        )
    }

}