package com.example.tifinbox.helper

import android.widget.RatingBar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tifinbox.ui.theme.appGreen

@Composable
fun RatingBar(
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = appGreen,
){
    var isHalfStar = (rating % 1) != 0.0

    Row(Modifier.height(15.dp)){
        for(index in 1..stars){
            Icon(
                tint = starsColor,
                contentDescription = "",
                imageVector = if(index<= rating){
                    Icons.Rounded.Star
                }else{
                    if (isHalfStar){
                        isHalfStar = false
                        Icons.AutoMirrored.Rounded.StarHalf
                    }else{
                        Icons.Rounded.StarOutline
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    RatingBar(3.5)
}